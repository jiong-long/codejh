package com.jianghu.core.func.exportWord;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import com.jianghu.core.tools.FileUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import sun.misc.BASE64Encoder;

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
		List<WordUser> userList = new ArrayList<WordUser>();
		userList.add(new WordUser("姓名", "密码"));
		userList.add(new WordUser("张三", "1234"));
		userList.add(new WordUser("李四", "1111"));
		dataMap.put("userList", userList);

		List<String> listList = new ArrayList<String>();
		listList.add("列表第一条记录");
		listList.add("列表第二条记录");
		listList.add("列表第三条记录");
		dataMap.put("listList", listList);

		// 先在word中插入一张图片，生成xml后，图片变为BASE64编码后的一大段字符串，将该字符串变为${img}
		String imgPath = FileUtil.getProjectPath() + "\\src\\main\\java\\com\\jianghu\\core\\func\\exportWord\\img.jpg";
		dataMap.put("img", getImageStr(imgPath));

		String fileName = FileUtil.getDeskPath() + "\\导出的Word.doc";
		try {
			createDoc(dataMap, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出Word
	 * 
	 * 1.创建带有格式的word文档，将该需要动态展示的数据使用变量符${username}替换。
	 * 
	 * 2.将刚刚创建的word文档另存为xml格式。
	 * 
	 * 3．编辑这个XMl文档去掉多余的xml标记,用文本编辑器打开,把里面需要替换的变量使用${username}【${username}中间可能有其他字符】全部删除.
	 * 
	 * @param dataMap
	 * @param fileName
	 * @throws Exception
	 */
	public static void createDoc(Map<String, Object> dataMap, String fileName) throws Exception {
		// 指定路径的第一种方式(根据某个类的相对路径指定)
		configuration.setClassForTemplateLoading(WordUtil.class, "");

		// 指定路径的第二种方式,我的路径是C:/
		// configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\jinlong\\Desktop\\"));

		Template template = null;

		// 输出文档路径及名称
		File outFile = new File(fileName);
		Writer out = null;
		FileOutputStream fos = null;
		try {
			template = configuration.getTemplate("template.xml");
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

	/**
	 * 获取图片BASE64编码后的字符串
	 * 
	 * 如果有多张图片需要遍历，注意替换w:binData和v:imagedata标签中的图片源，不能一致，否则导出的图片一样
	 * 
	 * @param imgFile
	 * @return
	 */
	private static String getImageStr(String imgFile) {
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}
}
