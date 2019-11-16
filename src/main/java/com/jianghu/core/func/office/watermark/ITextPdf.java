package com.jianghu.core.func.office.watermark;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.jianghu.core.tools.Log;

/**
 * iText 增加水印
 * @author wangjinlong
 *
 */
public class ITextPdf {
	
	public static void main(String[] args) throws Exception {
		addWatermark();
	}
	
	/**
	 * 增加水印
	 * @throws DocumentException 
	 * @throws IOException 
	 */
	public static void addWatermark() {
		// 待加水印的文件
        PdfReader reader = null;
        // 加完水印的文件
        PdfStamper stamper = null;
		try {
			reader = new PdfReader("/Users/wangjinlong/Desktop/加不了水印.pdf");
			stamper = new PdfStamper(reader, new FileOutputStream("/Users/wangjinlong/Desktop/水印1.pdf"));
			int total = reader.getNumberOfPages() + 1;
			PdfContentByte content;
			//透明度
			PdfGState gs = new PdfGState();
			gs.setFillOpacity(0.15f);
			// 字体设置支持中文
			BaseFont basefont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			//倾斜
			float rotation = 30;
			//水印文本
			String markText = "新地能源";
			// 循环对每页插入水印
			for (int i = 1; i < total; i++) {
				// 水印的起始(在内容上方显示)
				content = stamper.getOverContent(1);
				// 水印的起始(在内容下方显示)
				// content = stamper.getUnderContent(i);
				// 开始
				content.beginText();
				// 透明度
				content.setGState(gs);
				// 设置颜色
				content.setColorFill(BaseColor.GRAY);
				// 设置字体及字号
				content.setFontAndSize(basefont, 55);
				// 设置起始位置
				content.setTextMatrix(0, 0);
				// 开始写入水印
				Rectangle pageRect = reader.getPageSizeWithRotation(i);//获取页
				float width = pageRect.getWidth();
				float height = pageRect.getHeight();
				content.showTextAligned(Element.ALIGN_CENTER, markText, width/3 + 10, height/4 * 3, rotation);
				content.showTextAligned(Element.ALIGN_CENTER, markText, width/3 * 2, height/2 + 40, rotation);
				content.showTextAligned(Element.ALIGN_CENTER, markText, width/2, height/5, rotation);
				content.endText();
			}
			stamper.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Log.error(e);
		} finally {
			if(stamper != null) {
				try {
					
				} catch (Exception e) {
					Log.error(e);
				}
			}
			if(reader != null) {
				reader.close();
			}
		}
	}
	
	/**
	 * 增加图片水印
	 * @param imgPath
	 * @return
	 */
	public static boolean addWatermarkPdfImg(String imgPath) {
	    PdfStamper stamper = null;
	    PdfReader reader = null;
	    boolean flag = false;
		try {
			reader = new PdfReader("/Users/wangjinlong/Desktop/111.pdf");
			stamper = new PdfStamper(reader, new FileOutputStream("/Users/wangjinlong/Desktop/222.pdf"));
			int total = reader.getNumberOfPages() + 1;
			PdfContentByte content;
			Image img = Image.getInstance(imgPath);
			img.setAbsolutePosition(30, 100);
			for (int i = 1; i < total; i++) {
				// 在内容上方加水印
				content = stamper.getOverContent(i);
				content.addImage(img);
			}
			flag = true;
		} catch (Exception e) {
			Log.error(e);
		} finally {
			if(stamper != null) {
				try {
					stamper.close();
				} catch (Exception e) {
					Log.error(e);
				}
			}
			if(reader != null) {
				reader.close();
			}
		}

	    return flag;
	}
}
