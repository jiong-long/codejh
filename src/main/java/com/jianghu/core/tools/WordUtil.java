package com.jianghu.core.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Word相关工具类
 * 
 * @author jinlong
 *
 */
public class WordUtil {
	private static Configuration configuration = null;

	static {
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
	}

	public static void main(String[] args) throws IOException, TemplateException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("username", "张三");
		dataMap.put("password", "123");

		String fileName = "C:\\Users\\jinlong\\Desktop\\导出Word.doc";
		try {
			createDoc(dataMap, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出Word
	 * 
	 * 1.创建带有格式的word文档，将该需要动态展示的数据使用变量符替换。
	 * 
	 * 2. 将刚刚创建的word文档另存为xml格式。
	 * 
	 * 3．编辑这个XMl文档去掉多余的xml标记,用文本编辑器打开,把里面需要替换的变量使用${username}【${username}中间可能有其他字符】全部删除.
	 * 
	 * @param dataMap
	 * @param fileName
	 * @throws Exception
	 */
	public static void createDoc(Map<String, Object> dataMap, String fileName) throws Exception {
		//指定路径的第一种方式(根据某个类的相对路径指定)  
		//configuration.setClassForTemplateLoading(this.getClass(),"");  

		//指定路径的第二种方式,我的路径是C:/
		configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\jinlong\\Desktop\\"));

		Template template = null;

		// 输出文档路径及名称
		File outFile = new File(fileName);
		Writer out = null;
		FileOutputStream fos = null;
		try {
			template = configuration.getTemplate("导出Word.xml");
			fos = new FileOutputStream(outFile);
			OutputStreamWriter oWriter = new OutputStreamWriter(fos, "UTF-8");
			out = new BufferedWriter(oWriter);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (template != null) {
					template.process(dataMap, out);
				}
				if (out != null) {
					out.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (TemplateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 读取Word内容
	 * 
	 * @param path
	 * @return
	 */
	public static String readWord(String path) {
		String buffer = "";
		try {
			if (path.endsWith(".doc")) {
				InputStream is = new FileInputStream(new File(path));
				WordExtractor ex = new WordExtractor(is);
				buffer = ex.getText();
				ex.close();
			} else if (path.endsWith("docx")) {
				OPCPackage opcPackage = POIXMLDocument.openPackage(path);
				POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
				buffer = extractor.getText();
				extractor.close();
			} else {
				System.out.println("此文件不是word文件！");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return buffer;
	}
}
