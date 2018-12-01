package com.jianghu.core.func.exportWord;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * word转pdf
 * 
 * @author wangjinlong
 * @createTime 2018年12月1日 下午5:21:07
 */
public class Word2Pdf {
	public static void main(String[] args) throws Exception {
		Word2Pdf.wordToPDF("C:\\Users\\wangjinlong\\Desktop\\template.docx",
				"C:\\Users\\wangjinlong\\Desktop\\template.pdf");
	}

	public static void wordToPDF(String wordFile, String pdfFile) throws Exception {
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

