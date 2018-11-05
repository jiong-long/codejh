package com.jianghu.core.func.single;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.jianghu.core.tools.NetUtil;

/**
 * 百度地图根据IP获取地理位置
 * 
 * @creatTime 2017年10月3日 下午11:11:07
 * @author jinlong
 * 
 */
public class Location {
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	// 根据IP获取坐标地址相关信息，ip为空默认查本机地址
	public static JSONObject readJsonFromUrl(String ip) throws Exception {
		// 这里调用百度的ip定位api服务 详见
		// http://api.map.baidu.com/lbsapi/cloud/ip-location-api.htm
		// IP不出现，或者出现且为空字符串的情况下，会使用当前访问者的IP地址作为定位参数。
		String url = "http://api.map.baidu.com/location/ip?ak=pCuWDaRFnEawujVcwB2WnpEaZLuOiYGc&coor=bd09ll&ip=" + ip;
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	/**
	 * 通过request获取客户端的地址（市）
	 * 
	 * @creatTime 2017年10月3日 下午11:41:42
	 * @author jinlong
	 * @param ip
	 * @return
	 * @throws Exception
	 */
	public static String getLocationFromRequest(HttpServletRequest request) throws Exception {
		String ip = NetUtil.getIpFromRequest(request);
		return getLocationFromIp(ip);
	}

	/**
	 * 获取服务器的城市
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年10月21日 下午10:53:04
	 * @return
	 * @throws Exception
	 */
	public static String getServerCity() {
		String city = "";
		try {
			String serverIp = NetUtil.REMOTE_IP;
			city = getLocationFromIp(serverIp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}

	/**
	 * 通过IP获取地址
	 * 
	 * @creatTime 2017年10月3日 下午11:40:24
	 * @author jinlong
	 * @param ip
	 * @return
	 * @throws Exception
	 */
	public static String getLocationFromIp(String ip) throws Exception {
		JSONObject json = readJsonFromUrl(ip);
		JSONObject content = (JSONObject) json.get("content");
		JSONObject addressDetail = (JSONObject) content.get("address_detail");
		String city = (String) addressDetail.get("city");
		return city;
	}

	public static void main(String[] args) throws Exception {
		// json中内容较多，包括坐标
		JSONObject json = readJsonFromUrl("180.102.207.209");
		System.out.println(json.toString());
		System.out.println(getLocationFromIp("180.102.207.209"));
	}
}
