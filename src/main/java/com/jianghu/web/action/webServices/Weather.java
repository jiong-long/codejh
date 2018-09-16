package com.jianghu.web.action.webServices;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jianghu.core.Tools;
import com.jianghu.core.func.Location;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 通过UrlConnection调用webServices服务获取天气相关信息
 * 
 * @author wangjinlong
 * 
 * @creatTime 2017年10月10日 下午9:10:55
 */
public class Weather extends ActionSupport {
	private static final long serialVersionUID = 7084116897130990445L;

	/**
	 * 获取天气信息
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年10月10日 下午9:11:19
	 * @return
	 */
	public String getWeatherInfo() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String city = Location.getServerCity();
			if ("".equals(city)) {
				city = request.getParameter("localtion");
			} else {//city带有市，要去掉
				city = city.substring(0, city.length() - 1);
			}
			Tools.returnJSONtoPage(getWeather(city));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 获取SOAP的请求头，并替换其中的标志符号为用户输入的城市
	 * 
	 * @param city
	 *            用户输入的城市名称
	 * @return 客户将要发送给服务器的SOAP请求
	 */
	private static String getSoapRequest(String city) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
				+ "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><getWeather xmlns=\"http://WebXml.com.cn/\"><theCityCode>" + city
				+ "</theCityCode></getWeather></soap:Body></soap:Envelope>");
		return sb.toString();
	}

	/**
	 * 用户把SOAP请求发送给服务器端，并返回服务器点返回的输入流
	 * 
	 * @param city
	 *            用户输入的城市名称
	 * @return 服务器端返回的输入流，供客户端读取
	 * @throws Exception
	 */
	private static InputStream getSoapInputStream(String city) throws Exception {
		try {
			String soap = getSoapRequest(city);
			if (soap == null) {
				return null;
			}
			URL url = new URL("http://www.webxml.com.cn/WebServices/WeatherWS.asmx");
			URLConnection conn = url.openConnection();
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			//加上这句会报 java.net.ProtocolException: Exceeding stated content length 
			//conn.setRequestProperty("Content-Length", Integer.toString(soap.length()));
			conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			conn.setRequestProperty("SOAPAction", "http://WebXml.com.cn/getWeather");

			OutputStream os = conn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
			osw.write(soap);
			osw.flush();
			osw.close();

			InputStream is = conn.getInputStream();
			return is;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 对服务器端返回的XML进行解析
	 * 
	 * @param city
	 *            用户输入的城市名称
	 * @return 字符串 用#分割
	 * @throws Exception
	 */
	public static JSONObject getWeather(String city) throws Exception {
		//使用DOM的方式解析XML
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputStream is = getSoapInputStream(city);
		Document doc = db.parse(is);
		NodeList nl = doc.getElementsByTagName("string");
		// http://www.webxml.com.cn/WebServices/WeatherWS.asmx?op=getWeather
		Node node = nl.item(0);
		String values = node.getFirstChild().getNodeValue();
		if (values == null || values.contains("查询结果为空")) {
			return null;
		}

		JSONObject dataObject = new JSONObject();
		dataObject.put("code", "0");
		dataObject.put("msg", "");
		dataObject.put("count", "5");
		JSONArray jsonArray = new JSONArray();
		//5组一个循环
		for (int count = 7; count < nl.getLength();) {
			JSONObject listObject = new JSONObject();
			values = nl.item(count).getFirstChild().getNodeValue();//<string>10月12日 小到中雨转阴</string>
			String[] dateAndWea = values.split(" ");
			listObject.put("date", dateAndWea[0]);
			listObject.put("weather", dateAndWea[1]);
			count++;

			values = nl.item(count).getFirstChild().getNodeValue();//<string>13℃/16℃</string>
			String[] temper = values.split("/");
			listObject.put("mintemper", temper[0]);
			listObject.put("maxtemper", temper[1]);
			count++;

			values = nl.item(count).getFirstChild().getNodeValue();//<string>东北风4-5级转3-4级</string>
			listObject.put("wind", values);
			count++;

			values = nl.item(count).getFirstChild().getNodeValue();//<string>21.gif</string>
			String icon1 = values;
			count++;

			values = nl.item(count).getFirstChild().getNodeValue();//<string>2.gif</string>
			String icon2 = values;
			count++;
			if (icon1.equals(icon2)) {//天气中没有转，是一个图标
				listObject.put("icon", "<img src='/jiong/images/weather/" + icon1 + "'></img>");
			} else {//是两个图标
				listObject.put("icon", "<img src='/jiong/images/weather/" + icon1 + "'>&nbsp;&nbsp;&nbsp;&nbsp;</img><img src='/jiong/images/weather/" + icon2 + "'></img>");
			}

			jsonArray.put(listObject);
		}
		dataObject.put("data", jsonArray);
		is.close();
		return dataObject;
	}

	public static void main(String[] args) throws Exception {
		getWeather("南京");
	}
}
