package com.common;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import com.google.common.collect.Lists;
import com.jianghu.domain.basic.POITestVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @description: easy_poi 工具类
 * @author: OF3848
 * @create: 2020-11-09 16:11
 */
public class EasyPoiUtil {
    public static void main(String[] args) throws IOException {
        //创建集合
        List<POITestVO> vos= Lists.newLinkedList();
        POITestVO vo1=POITestVO.builder().no(1).name("测试1").num(10).remark("备注备注11111111").build();
        POITestVO vo2=POITestVO.builder().no(1).name("测试2").num(20).remark("备注备注22222").build();
        POITestVO vo3=POITestVO.builder().no(3).name("测试3").num(30).remark("备注备注333333").build();
        POITestVO vo4=POITestVO.builder().no(3).name("测试4").num(30).remark("备注备注444441").build();
        vos.add(vo1);
        vos.add(vo2);
        vos.add(vo3);
        vos.add(vo4);

        List<POITestVO> vos2 = Lists.newLinkedList();
        POITestVO vo12=POITestVO.builder().no(1).name("测试1").num(10).remark("备注备注11111111").build();
        POITestVO vo22=POITestVO.builder().no(1).name("测试2").num(20).remark("备注备注22222").build();
        POITestVO vo32=POITestVO.builder().no(3).name("测试3").num(30).remark("备注备注333333").build();
        POITestVO vo42=POITestVO.builder().no(3).name("测试4").num(30).remark("备注备注444441").build();
        vos2.add(vo12);
        vos2.add(vo22);
        vos2.add(vo32);
        vos2.add(vo42);

        //生成workbook
        Workbook workbook = new HSSFWorkbook();
        ExportParams exportParams0 = new ExportParams();
        exportParams0.setSheetName("汇总");
        ExportParams exportParams1 = new ExportParams();
        exportParams1.setSheetName("删除明细");

        ExcelExportService service0 = new ExcelExportService();
        service0.createSheet(workbook, exportParams0, POITestVO.class, vos);
        ExcelExportService service1 = new ExcelExportService();
        service1.createSheet(workbook, exportParams1, POITestVO.class, vos2);

        //导出
        OutputStream out = new FileOutputStream(new File("/Users/wangjinlong/Desktop/交易分析.xls"));
        workbook.write(out);
    }
}
