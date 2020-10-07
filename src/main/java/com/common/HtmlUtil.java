package com.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

/**
 * Jsoup对html相关操作
 * 
 * @creatTime 2018年11月18日 下午3:35:46
 * @author jinlong
 */
public class HtmlUtil {
	public static void main(String[] args) throws IOException {
		getFormData("https://www.yiibai.com/login", "login-form");
	}

	/**
	 * 获取网页的title
	 * 
	 * @param htmlPath
	 * @return
	 * @throws IOException
	 */
	public static String getHead(String htmlPath) throws IOException {
		Document doc = Jsoup.connect(htmlPath).get();
		//本地html,通过这种方式
		//Document doc = Jsoup.parse(new File("e:\\register.html"),"utf-8");
		return doc.title();
	}

	/**
	 * 获取页面上所有的链接，key是内容，value是链接
	 * 
	 * @param htmlPath
	 * @throws IOException
	 */
	public static Map<String, String> getAllUrl(String htmlPath) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		Document doc = Jsoup.connect(htmlPath).get();
		Elements links = doc.select("a[href]");
		for (Element link : links) {
			if (link.attr("href").startsWith("http")) {
				map.put(link.text(), link.attr("href"));
			}
		}
		return map;
	}

	/**
	 * 获取所有的图片
	 * 
	 * @param htmlPath
	 * @throws IOException
	 */
	public static void getAllImage(String htmlPath) throws IOException {
		Document doc = Jsoup.connect(htmlPath).get();
		Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
		for (Element image : images) {
			System.out.println("src : " + image.attr("src"));
			System.out.println("height : " + image.attr("height"));
			System.out.println("width : " + image.attr("width"));
			System.out.println("alt : " + image.attr("alt"));
		}
	}

	/**
	 * 获取表单数据
	 * 
	 * @param htmlPath
	 *            网页地址
	 * @param formId
	 *            form id
	 * @throws IOException
	 */
	public static void getFormData(String htmlPath, String formId) throws IOException {
		Document doc = Jsoup.connect(htmlPath).get();
		Element loginform = doc.getElementById(formId);

		Elements inputElements = loginform.getElementsByTag("input");
		for (Element inputElement : inputElements) {
			String key = inputElement.attr("name");
			String value = inputElement.attr("value");
			System.out.println("Param name: " + key + " \nParam value: " + value);
		}
	}

	/**
	 * 使用Whitelist对输入的Html文档过滤，只允许特定的标签或者属性，防止恶意代码。
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年9月28日 下午2:50:57
	 * @param str
	 * @return
	 */
	public static String removeHtml(String str) {
		return Jsoup.clean(str, Whitelist.none());
	}
}
