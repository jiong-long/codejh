package com.jianghu.core.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jianghu.core.Tools;

/**
 * excel读写工具类（兼容xls和xlsx）
 * 
 * @creatTime 2016年8月21日
 */
public class ExcelUtil {
	private static Logger logger = Logger.getLogger(ExcelUtil.class);
	private final static String xls = "xls";
	private final static String xlsx = "xlsx";

	/**
	 * demo 测试方法
	 * 
	 * @creatTime 2017年5月6日 下午1:47:57
	 * @author jinlong
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		String filePath = "F:\\123.xls"; // 兼容xlsx
		// 读取excel数据，不包含第一行标题
		List<Object[]> list = ExcelUtil.readExcel(filePath);

		// 将list中的数据写入到excel中，第一行作为标题
		HSSFWorkbook workbook = writeExcel(list);
		OutputStream outputStream = new FileOutputStream("H:\\12345.xls");
		workbook.write(outputStream);
		outputStream.close();
	}

	/**
	 * 将读入的excel数据以流的方式返回前台提供下载
	 * 
	 * @creatTime 2017年5月21日 上午1:32:35
	 * @author jinlong
	 * @param response
	 *            HttpServletResponse
	 * @param list
	 *            数据集合（第一行为标题）
	 * @param fileName
	 *            文件名
	 * @throws IOException
	 */
	public static void createExcelAndDownload(HttpServletResponse response, List<Object[]> list,
			String fileName) throws IOException {
		// 调用方法创建HSSFWorkbook工作簿对象
		HSSFWorkbook workbook = writeExcel(list);
		// 名称需要重新编码，否则显示不正常
		fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");

		// 文件下载头标题
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

		// 将工作薄写入到输出流中
		OutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		// 关闭流
		outputStream.close();
	}

	/**
	 * 传入excel的磁盘路径，返回一个List<String[]>(不包括第一行的表头)
	 * 
	 * @param filePath
	 *            excel的路径
	 * @throws IOException
	 */
	public static List<Object[]> readExcel(String filePath) throws IOException {
		// 检查文件
		checkFile(filePath);
		// 获得Workbook工作薄对象
		Workbook workbook = getWorkBook(filePath);
		// 创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
		List<Object[]> list = new ArrayList<Object[]>();
		if (workbook != null) {
			for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
				// 获得当前sheet工作表
				Sheet sheet = workbook.getSheetAt(sheetNum);
				if (sheet == null) {
					continue;
				}
				// 获得当前sheet的开始行
				int firstRowNum = sheet.getFirstRowNum();
				// 获得当前sheet的结束行
				int lastRowNum = sheet.getLastRowNum();
				// 循环除了第一行的所有行
				for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
					// 获得当前行
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}
					// 获得当前行的开始列
					int firstCellNum = row.getFirstCellNum();
					if (firstCellNum < 0) {
						continue;
					}
					// 获得当前行的列数
					int lastCellNum = row.getPhysicalNumberOfCells();
					Object[] cells = new Object[row.getPhysicalNumberOfCells()];
					// 循环当前行
					for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
						Cell cell = row.getCell(cellNum);
						cells[cellNum] = getCellValue(cell);
					}
					list.add(cells);
				}
			}
			workbook.close();
		}
		return list;
	}

	/**
	 * 检查文件文件类型与是否存在
	 * 
	 * @creatTime 2017年5月21日 上午1:38:17
	 * @author jinlong
	 * @param filePath
	 * @throws IOException
	 */
	public static void checkFile(String filePath) throws IOException {
		// 判断文件是否存在
		if (Tools.isEmpty(filePath)) {
			logger.error("文件不存在！");
			throw new FileNotFoundException("文件不存在！");
		}
		// 判断文件是否是excel文件
		if (!filePath.toLowerCase().endsWith(xls) && !filePath.toLowerCase().endsWith(xlsx)) {
			logger.error(filePath + "不是excel文件");
			throw new IOException(filePath + "不是excel文件");
		}
	}

	/**
	 * 获得HSSFWorkbook工作薄对象
	 * 
	 * @creatTime 2017年5月21日 上午1:38:04
	 * @author jinlong
	 * @param filePath
	 * @return
	 */
	public static Workbook getWorkBook(String filePath) {
		// 创建Workbook工作薄对象，表示整个excel
		Workbook workbook = null;
		try {
			// 获取excel文件的io流
			InputStream is = new FileInputStream(filePath);

			// 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
			if (filePath.toLowerCase().endsWith(xls)) {
				// 2003
				workbook = new HSSFWorkbook(is);
				// 不兼容可以试下这个方法
				// workbook = new HSSFWorkbook(new POIFSFileSystem(is));
			} else if (filePath.toLowerCase().endsWith(xlsx)) {
				// 2007
				workbook = new XSSFWorkbook(is);
			}
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
		return workbook;
	}

	/**
	 * 创建HSSFWorkbook工作薄对象，并添加数据
	 * 
	 * @creatTime 2017年5月21日 上午1:36:42
	 * @author jinlong
	 * @param list
	 * @return
	 */
	public static HSSFWorkbook writeExcel(List<Object[]> list) {
		try {
			// 创建工作薄对象
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建标题行样式
			HSSFCellStyle headStyle = headStyle(workbook);
			// 创建内容行样式
			HSSFCellStyle contentStyle = contentStyle(workbook);
			// 创建sheet
			HSSFSheet sheet = workbook.createSheet("sheet");
			// 设置表的默认列宽
			sheet.setDefaultColumnWidth(30);

			// 标题行添加数据和样式
			HSSFRow headRow = sheet.createRow(0);
			Object[] headCells = list.get(0);
			for (int j = 0; j < headCells.length; j++) {// 遍历该行的列数据
				HSSFCell cell = headRow.createCell(j);
				cell.setCellValue(headCells[j] + "");
				cell.setCellStyle(headStyle);
			}

			// 内容行添加数据和样式
			for (int i = 1; i < list.size(); i++) {// 遍历所有的数据行
				HSSFRow contentRow = sheet.createRow(i);
				Object[] contentCells = list.get(i);
				for (int j = 0; j < contentCells.length; j++) {// 遍历该行的列数据
					HSSFCell cell = contentRow.createCell(j);
					cell.setCellValue(contentCells[j] + "");
					cell.setCellStyle(contentStyle);
				}
			}
			return workbook;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	/**
	 * 创建标题行样式
	 * 
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle headStyle(HSSFWorkbook workbook) {
		HSSFCellStyle headStyle = workbook.createCellStyle(); // 创建样式对象
		HSSFFont headFont = workbook.createFont(); // 创建字体
		headFont.setFontName("微软雅黑");// 字体
		headFont.setBold(true);// 是否加粗
		headFont.setColor(HSSFFont.COLOR_RED);// 颜色
		headStyle.setAlignment(HorizontalAlignment.CENTER);// 对其方式
		headStyle.setFont(headFont);
		return headStyle;
	}

	/**
	 * 创建内容行样式
	 * 
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle contentStyle(HSSFWorkbook workbook) {
		HSSFCellStyle contentStyle = workbook.createCellStyle();
		HSSFFont contentFont = workbook.createFont();
		contentFont.setFontName("微软雅黑");
		contentFont.setBold(false);
		contentFont.setColor(HSSFFont.COLOR_NORMAL);
		contentStyle.setAlignment(HorizontalAlignment.CENTER);
		contentStyle.setFont(contentFont);
		return contentStyle;
	}

	/**
	 * 获取单元格的内容
	 * 
	 * @creatTime 2017年5月21日 上午1:37:19
	 * @author jinlong
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}

		// 判断数据的类型
		if (cell.getCellTypeEnum() == CellType.NUMERIC) { // 数字
			// 日期类型也是数字，表示当前时间与1900年1月1日相隔的天数
			if (HSSFDateUtil.isCellDateFormatted(cell))
				cellValue = DateUtil.toY_M_D_H_M_S(HSSFDateUtil.getJavaDate(cell
						.getNumericCellValue()));
			else {
				cellValue = new DecimalFormat("#.##").format(cell.getNumericCellValue());
			}
		} else if (cell.getCellTypeEnum() == CellType.STRING) { // 字符串
			cellValue = String.valueOf(cell.getStringCellValue());
		} else if (cell.getCellTypeEnum() == CellType.BOOLEAN) { // Boolean
			cellValue = String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellTypeEnum() == CellType.FORMULA) { // 公式
			//cellValue = String.valueOf(cell.getCellFormula());//获取公式的内容
			try{
				cellValue = new DecimalFormat("#.##").format(cell.getNumericCellValue());
			} catch(Exception e){
				cellValue = cell.getStringCellValue();
			}
		} else if (cell.getCellTypeEnum() == CellType.BLANK) { // 空值
			cellValue = "";
		} else if (cell.getCellTypeEnum() == CellType.ERROR) { // 故障
			cellValue = "非法字符";
		} else {
			cellValue = "未知类型";
		}

		// // 旧版本的poi，需要使用下面的方法取代
		// // 判断数据的类型
		// switch (cell.getCellType()) {
		// case Cell.CELL_TYPE_NUMERIC: // 数字
		// // 日期类型也是数字，表示当前时间与1900年1月1日相隔的天数
		// if (HSSFDateUtil.isCellDateFormatted(cell))
		// cellValue =
		// DateUtil.toY_M_D_H_M_S(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
		// else {
		// cellValue = new
		// DecimalFormat("#.##").format(cell.getNumericCellValue());
		// }
		// break;
		// case Cell.CELL_TYPE_STRING: // 字符串
		// cellValue = String.valueOf(cell.getStringCellValue());
		// break;
		// case Cell.CELL_TYPE_BOOLEAN: // Boolean
		// cellValue = String.valueOf(cell.getBooleanCellValue());
		// break;
		// case Cell.CELL_TYPE_FORMULA: // 公式
		// cellValue = String.valueOf(cell.getCellFormula());
		// break;
		// case Cell.CELL_TYPE_BLANK: // 空值
		// cellValue = "";
		// break;
		// case Cell.CELL_TYPE_ERROR: // 故障
		// cellValue = "非法字符";
		// break;
		// default:
		// cellValue = "未知类型";
		// break;
		// }

		return cellValue;
	}
}
