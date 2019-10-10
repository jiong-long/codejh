package com.jianghu.core.func.office;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.TextWatermark;
import com.spire.doc.documents.WatermarkLayout;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.graphics.PdfBrushes;
import com.spire.pdf.graphics.PdfFont;
import com.spire.pdf.graphics.PdfFontFamily;
import com.spire.pdf.graphics.PdfStringFormat;
import com.spire.pdf.graphics.PdfTextAlignment;
import com.spire.pdf.graphics.PdfTilingBrush;
import com.spire.pdf.widget.PdfPageCollection;

public class AddWatermark {
	public static void main(String[] args) {
		//增加水印 PDF
		addWatermarkPdf();
		//增加水印
		//addWatermark();
		//去掉水印
		//deleteWatermark();
	}
	
	public static void addWatermarkPdf() {
		//加载Word文档
		PdfDocument pdf = new PdfDocument();
		pdf.loadFromFile("/Users/wangjinlong/Documents/项目资料/新地/1.出厂包/新地集群部署手册_v1.1(1).pdf");
		pdf.getPages().add();
		
		String watermark = "ENN";
		PdfTilingBrush brush = null;
		//获取文档第1页
        PdfPageCollection pageArr = pdf.getPages();
        for (int i = 0; i < pageArr.getCount(); i++) {
        	if(brush == null) {
        		Dimension2D dimension2D = new Dimension();
                dimension2D.setSize(pageArr.get(i).getCanvas().getClientSize().getWidth() / 2, pageArr.get(i).getCanvas().getClientSize().getHeight() / 3);
                brush = new PdfTilingBrush(dimension2D);
                brush.getGraphics().setTransparency(0.4F);
                brush.getGraphics().save();
                brush.getGraphics().translateTransform((float) brush.getSize().getWidth() / 2, (float) brush.getSize().getHeight() / 2);
                brush.getGraphics().rotateTransform(-45);
                brush.getGraphics().drawString(watermark, new PdfFont(PdfFontFamily.Helvetica, 30), PdfBrushes.getViolet(), 0, 0, new PdfStringFormat(PdfTextAlignment.Center));
                brush.getGraphics().restore();
                brush.getGraphics().setTransparency(1);
        	}
			addWatermarkPdfPage(brush, pageArr.get(i), watermark);
		}
        //保存
		pdf.saveToFile("/Users/wangjinlong/Desktop/有水印1.pdf");
		
		//加载Word文档
		PdfDocument pdf1 = new PdfDocument();
		pdf1.loadFromFile("/Users/wangjinlong/Desktop/有水印1.pdf");
		PdfPageCollection pageArr1 = pdf1.getPages();
		pageArr1.remove(pageArr1.get(pageArr1.getCount() - 1));
		pdf1.saveToFile("/Users/wangjinlong/Desktop/有水印2.pdf");
		
	}
	
	public static void addWatermarkPdfPage(PdfTilingBrush brush, PdfPageBase page, String watermark) {
        Rectangle2D loRect = new Rectangle2D.Float();
        loRect.setFrame(new Point2D.Float(0, 0), page.getCanvas().getClientSize());
        page.getCanvas().drawRectangle(brush, loRect);
	}
	
	/**
	 * 增加水印
	 */
	public static void addWatermark() {
		//加载Word文档
		Document doc = new Document();
		doc.loadFromFile("/Users/wangjinlong/Desktop/新地单点登录切换.docx");
 
		//创建TextWatermark实例
		TextWatermark textWatermark = new TextWatermark();
		//设置水印文本
		textWatermark.setText("LUCULENT");
		//自定义文本水印的属性设置（字体、字号、字体颜色和排版格式）
		textWatermark.setFontName("Arial");
		textWatermark.setFontSize(60f);
		textWatermark.setColor(Color.gray);
		textWatermark.setLayout(WatermarkLayout.Diagonal);
		
		//创建PictureWatermark实例
		//PictureWatermark imageWatermark = new PictureWatermark();
		//设置水印图片（可以是本地图片也可以来自流）
		//imageWatermark.setPicture("/Users/wangjinlong/Desktop/baidu.png");
		//imageWatermark.isWashout(false);
		 
		//将文本水印添加到文档
		doc.setWatermark(textWatermark);
		
		//保存
		doc.saveToFile("/Users/wangjinlong/Desktop/有水印2.docx", FileFormat.Docx_2013);
	}
	
	public static void deleteWatermark() {
		//加载Word文档
		Document doc = new Document();
		doc.loadFromFile("/Users/wangjinlong/Desktop/14项目需求变更申请单11.doc");
	 
		//删除水印
		doc.setWatermark(null);
	 
		//保存
		doc.saveToFile("/Users/wangjinlong/Desktop/14项目需求变更申请单22.doc", FileFormat.Docx_2013);
	}
}
