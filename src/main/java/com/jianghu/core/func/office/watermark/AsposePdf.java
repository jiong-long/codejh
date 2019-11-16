package com.jianghu.core.func.office.watermark;

import com.aspose.pdf.Document;
import com.aspose.pdf.ImageStamp;
import com.aspose.pdf.HorizontalAlignment;
import com.aspose.pdf.VerticalAlignment;

/**
 * Aspose 增加水印
 * 
 * @author wangjinlong
 *
 */
public class AsposePdf {
	public static void main(String[] args) {
		insertWatermarkText("/Users/wangjinlong/Desktop/原件.pdf", "11111111111111");
	}
	
	public static void insertWatermarkText(String pdfFile, String watermarkText){
		Document pdfDocument = new Document(pdfFile);
	    ImageStamp imageStamp = new ImageStamp("/Users/wangjinlong/Downloads/11.png");
	    imageStamp.setBackground(true);
	    imageStamp.setHeight(350);
	    imageStamp.setWidth(350);
	    imageStamp.setOpacity(0.5);
	    imageStamp.setHorizontalAlignment(HorizontalAlignment.Center);
	    imageStamp.setVerticalAlignment(VerticalAlignment.Center);
	    for (int j = 1; j <= pdfDocument.getPages().size(); j++) {
	        pdfDocument.getPages().getUnrestricted(j).addStamp(imageStamp);
	    }
	    pdfDocument.save("/Users/wangjinlong/Desktop/111.pdf");
	}
}
