package com.jianghu.core.func.office.topdf;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.util.StopWatch;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.jianghu.core.tools.Log;

/**
 * word转pdf
 * 
 * @author wangjinlong
 * @createTime 2018年12月1日 下午5:21:07
 */
public class Word2Pdf {
	public static void main(String[] args) throws Exception {
		word2Pdf("/Users/wangjinlong/Desktop/原件.doc", "/Users/wangjinlong/Desktop/19加粗.pdf");
	}
	
	/**
	 * 使用aspose将word转为PDF
	 * @param wordFile
	 * @param pdfFile
	 */
	public static void word2Pdf(String wordFile, String pdfFile) {
		FileOutputStream fileOutputStream = null;
		if (!getLicense()) {
			System.out.println("获取License失败");
			Log.error("获取License失败");
			return;
		}
		try {
			Document document = new Document(wordFile);
			fileOutputStream = new FileOutputStream(new File(pdfFile));
			document.save(fileOutputStream, SaveFormat.PDF);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			Log.error(exception);
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException iOException) {
				Log.error(iOException);
			}
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
	    } catch (Exception exception) {
	    	Log.error(exception);
	    } 
	    return bool;
	}

	
	/**
	 * 这个不好用，需要安装软件，而且转换时，字体样式不正确，页数也不正确
	 * 多平台，使用openoffice将word转为PDF
	 * 1、http://www.openoffice.org/zh-cn/download/ 去官网下载对应版本软件
	 * 2、将压缩包上传至服务器上，并进行解压安装（Linux）。
	  		tar -zxvf  对应的压缩包名字
			cd 进入解压后的 /zh-cn/RPMS
			yum localinstall *.rpm
			cd desktop-integration
			rpm -ivh openoffice4.1.5-redhat-menus-4.1.5-9789.noarch.rpm
	 * 3、启动服务（后台启动）
	 		nohup /opt/openoffice4/program/soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard &
	 		mac
	 		nohup /Applications/OpenOffice.app/Contents/MacOS/soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard &
	 		
	 * 4、装完openoffice后启动服务可能会报错：no suitable windowing system found, exiting从字面上的意思就是缺少一个窗口化的系统。所以就安装一个。
			yum groupinstall "X Window System"
		error while loading shared libraries: libXext.so.6: cannot open shared object file: No such file or directory
			sudo yum -y install libXext
	 */
	public static boolean wordToPdf(String wordFile, String pdfFile) {
		try {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			
            File inputFile = new File(wordFile);
            if (!inputFile.exists()) {
                // 找不到源文件, 则返回false
                return false;
            }
            // 如果目标路径不存在, 则新建该路径
            File outputFile = new File(pdfFile);
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }
            //如果目标文件存在，则删除
            if (outputFile.exists()) {
                outputFile.delete();
            }
            OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
            connection.connect();
            DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
            converter.convert(inputFile, outputFile);
            connection.disconnect();
            
            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint());
            return true;
        } catch (Exception e) {
        	Log.error(e);
        	System.err.println(e.getMessage());
        }
        return false;
	}

	/**
	 * window平台下，使用JCO将word转为PDF
	 * @param wordFile
	 * @param pdfFile
	 * @throws Exception
	 */
	public static void wordToPdfOnWindow(String wordFile, String pdfFile) throws Exception {
		ActiveXComponent app = null;
		// 如果报找不到Dispatch需要讲dll文件放在jdk（jre）的bin目录
		Dispatch doc = null;
		try {
			app = new ActiveXComponent("Word.Application");
			// 设置word不可见
			app.setProperty("Visible", new Variant(false));
			// 打开word文件
			Dispatch docs = app.getProperty("Documents").toDispatch();
			// doc = Dispatch.call(docs, "Open" , sourceFile).toDispatch();
			doc = Dispatch.invoke(docs, "Open", Dispatch.Method,
					new Object[] { wordFile, new Variant(false), new Variant(true) }, new int[1])
					.toDispatch();
			File tofile = new File(pdfFile);
			if (tofile.exists()) {
				tofile.delete();
			}
			// Dispatch.call(doc, "SaveAs", destFile, 17);
			// Variant(8)其中8表示word转html;7表示word转txt;44表示Excel转html;17表示word转成pdf。。
			// 如果报Invoke of : SaveAs,为服务器系统缺少软件Microsoft Save as PDF或 XPS，下载安装即可
			// http://www.microsoft.com/downloads/zh-cn/details.aspx?FamilyID=4D951911-3E7E-4AE6-B059-A2E79ED87041
			Dispatch.invoke(doc, "SaveAs", Dispatch.Method,
					new Object[] { pdfFile, new Variant(17) }, new int[1]);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Dispatch.call(doc, "Close", false);
			System.out.println("关闭文档");
			if (app != null)
				app.invoke("Quit", new Variant[] {});
		}
		ComThread.Release();
	}
}

