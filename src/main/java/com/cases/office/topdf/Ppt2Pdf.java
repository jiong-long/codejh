package com.cases.office.topdf;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.aspose.slides.License;
import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;
import com.common.Log;

public class Ppt2Pdf {
	public static void main(String[] args) {
		ppt2Pdf("/Users/wangjinlong/Documents/项目资料/新地/0.Zzzzzz/智慧建造系统方案汇报-V3.0.pptx", "/Users/wangjinlong/Desktop/333.pdf");
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

	public static void ppt2Pdf(String paramString1, String paramString2) {
		FileOutputStream fileOutputStream = null;
		if (!getLicense()) {
			Log.error("获取License失败");
			return;
		}
		try {
			Presentation presentation = new Presentation(paramString1);
			fileOutputStream = new FileOutputStream(new File(paramString2));
			presentation.save(fileOutputStream, SaveFormat.Pdf);
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException e) {
				Log.error(e);
			}
		}
	}
}
