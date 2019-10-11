package com.jianghu.core.func.office.topdf;

import java.io.ByteArrayInputStream;

import com.aspose.cells.HtmlSaveOptions;
import com.aspose.cells.License;
import com.aspose.cells.Workbook;
import com.jianghu.core.tools.Log;

/**
 * excel转为PDF
 * @author wangjinlong
 *
 */
public class Excel2Pdf {
	public static void main(String[] args) {
		excel2Pdf("/Users/wangjinlong/Documents/项目资料/新地/程序表.xlsx", "/Users/wangjinlong/Desktop/222.pdf");
	}
	
	public static void excel2Pdf(String excelFile, String pdfFile) {
		if (!getLicense()) {
			Log.error("获取License失败");
			return;
		}
		try {
			HtmlSaveOptions htmlSaveOptions = new HtmlSaveOptions(12);
			Workbook workbook = new Workbook(excelFile);
			workbook.save(pdfFile, htmlSaveOptions);
		} catch (Exception exception) {
			Log.error(exception);
		}
	}
	
	public static boolean getLicense() {
		boolean bool = false;
		try {
			String str = "<License><Data><Products><Product>Aspose.Total for Java</Product><Product>Aspose.Words for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry><SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber></Data><Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature></License>";
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
			License license = new License();
			license.setLicense(byteArrayInputStream);
			bool = true;
		} catch (Exception e) {
			Log.error(e);
		}
		return bool;
	}
}
