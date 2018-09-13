package com.jianghu.web.action.pcAndMail;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.jianghu.core.Database;
import com.jianghu.core.Tools;
import com.jianghu.core.func.SequenceThread;
import com.jianghu.core.tools.ExcelUtil;
import com.jianghu.core.tools.FileUtil;
import com.jianghu.core.tools.MailUtils;
import com.jianghu.domain.general.PaginationRequest;
import com.jianghu.domain.general.PaginationResponse;
import com.jianghu.domain.pcAndMail.Mail;
import com.jianghu.domain.pcAndMail.Mails;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 爬虫和邮件Action
 * 
 * @creatTime 2016年10月12日 下午9:51:07
 * @author jinlong
 * 
 */
public class PcAndMailAction extends ActionSupport implements ModelDriven<PaginationRequest> {

	private static final long serialVersionUID = 1L;

	// 分页
	private PaginationRequest paginationRequest = new PaginationRequest();

	@Override
	public PaginationRequest getModel() {
		return paginationRequest;// 自动封装rows和page到paginationRequest
	}

	// 附件下载
	private InputStream input;

	public InputStream getInput() {
		return input;
	}

	// 附件下载文件名称
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	// 附件上传必要的三个属性
	private File myUpload;
	// 可以不加这句的，略微有点强迫症
	@SuppressWarnings("unused")
	private String myUploadContentType;
	private String myUploadFileName;

	public void setMyUpload(File myUpload) {
		this.myUpload = myUpload;
	}

	public void setMyUploadContentType(String myUploadContentType) {
		this.myUploadContentType = myUploadContentType;
	}

	public void setMyUploadFileName(String myUploadFileName) {
		this.myUploadFileName = myUploadFileName;
	}

	/**
	 * 将获取的mails保存到数据库
	 * 
	 * @creatTime 2016年10月23日 下午8:59:43
	 * @author jinlong
	 * @return
	 */
	public String saveMailtoDB() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String web = request.getParameter("params").trim();
		List<Object[]> list = new ArrayList<Object[]>();
		try {
			URL url = new URL(web);
			// 邮件格式的正则表达式
			String s = "[a-zA-Z_0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z]+){1,3}";
			Pattern p = Pattern.compile(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String buf = null;
			while ((buf = br.readLine()) != null) {
				Matcher m = p.matcher(buf);
				while (m.find()) {
					list.add(new Object[] { SequenceThread.instance("MAILS_SEQ").getNextVal(), m.group() });
				}
			}
		} catch (Exception e) {
			// 网站可能会存在一些需要登录或者验证的消息会导致获取失败，失败时也需要将已经取到的数据保存到数据库
		} finally {
			try {
				String sql = "insert into MAILS(id,mails) values(?,?)";
				Database.executeUpdateBatch(sql, list);
			} catch (Exception e) {
				return NONE;
			}
		}
		return NONE;
	}

	/**
	 * 从数据库中获取所有邮件
	 * 
	 * @creatTime 2016年10月23日 下午10:19:37
	 * @author jinlong
	 * @return
	 */
	public String getMailfromDB() {
		int page = paginationRequest.getPage();
		int rows = paginationRequest.getRows();
		// 每页显示条数
		int number = (rows == 0) ? 10 : rows;
		// 每页的开始记录 第一页为1 第二页为number +1
		int start = (page - 1) * number + 1;
		int end = start + number - 1;

		// 分页查询
		String sql = "select distinct * from MAILS where id between ? and ? order by id";
		List<Mails> list = Database.executeQuery(sql, Mails.class, start, end);

		String countSql = "select count(distinct mails) from MAILS";
		String count = Database.getUniqueStringValue(countSql);

		// 将结果封装到分页类中
		PaginationResponse paginationResponse = new PaginationResponse();
		paginationResponse.setRows(list);
		paginationResponse.setTotal(Integer.parseInt(count));
		try {
			Tools.serializerObjectInclude(paginationResponse, new String[] { "*.total", "*.id", "*.mails" });
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 发送邮件
	 * 
	 * @creatTime 2016年10月25日 下午7:05:23
	 * @author jinlong
	 * @return
	 */
	public String sendMail() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String mails = request.getParameter("params");
		String[] mailsStr = mails.split(",");
		for (int i = 0; i < mailsStr.length; i++) {
			if (!Tools.isEmpty(mailsStr[i])) {
				// 发送邮件
				try {
					Properties prop = new Properties();
					// 利用类加载器获取src下配置文件的方法
					prop.load(this.getClass().getClassLoader().getResourceAsStream("email.properties"));

					String host = prop.getProperty("host");
					String username = prop.getProperty("username");
					String password = prop.getProperty("password");
					Session session = MailUtils.createSession(host, username, password);

					// 主题
					String subject = "来自江湖的邮件";
					// 内容
					String content = "有人的地方就有江湖";
					// 发件人
					String from = prop.getProperty("from");
					// 收件人
					String to = mailsStr[i];
					Mail mail = new Mail(from, to, subject, content);
					MailUtils.send(session, mail);
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return NONE;
	}

	/**
	 * 将数据导出到excel,以流的方式提供下载
	 * 
	 * @creatTime 2017年5月21日 下午2:40:26
	 * @author jinlong
	 * @return
	 * @throws IOException
	 */
	public String excelExport() throws IOException {
		// 查询所有的数据
		String sql = "select distinct * from MAILS order by id";
		List<Mails> list = Database.executeQuery(sql, Mails.class);

		// 将List<Mails>换为List<Object[]>,并将表头加上
		List<Object[]> listObj = new ArrayList<Object[]>();
		listObj.add(new Object[] { "序号", "EMAIL" });

		for (Mails mail : list) {
			listObj.add(new Object[] { mail.getId(), mail.getMails() });
		}

		// 创建Excel对象
		HSSFWorkbook workbook = ExcelUtil.writeExcel(listObj);
		// 讲Excel写入到outputStream中
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		// 讲outputStream转换为inputStream
		byte[] fileContent = outputStream.toByteArray();
		input = new ByteArrayInputStream(fileContent);

		// 解决中文文件名乱码的问题
		this.fileName = "邮箱.xls";
		this.fileName = new String(this.fileName.getBytes("GBK"), "ISO-8859-1");

		return "successExport";
	}

	/**
	 * 导入Excel中的数据
	 * 
	 * 已将文件保存到本地目录，所以导入到数据库的功能懒得写
	 * 
	 * 好的办法是直接通过流读取Excel,不保存到本地
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年5月22日 下午11:19:40
	 * @return
	 * @throws IOException
	 */
	public String excelImport() throws IOException {
		// 获得到本地磁盘的真实路径
		String savePath = ServletActionContext.getServletContext().getRealPath("/文档");
		savePath = savePath + "\\" + myUploadFileName;
		if (myUpload == null) {
			return SUCCESS;
		}
		FileUtil.copyFile(myUpload, savePath);
		List<Object[]> list = ExcelUtil.readExcel(savePath);
		for (Object[] objects : list) {
			for (Object object : objects) {
				// 将所有的单元格的值打出来瞅瞅
				System.out.println(object);
			}
		}
		// 删除上传的临时文件
		File file = new File(savePath);
		file.delete();
		return SUCCESS;
	}
}
