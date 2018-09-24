package com.jianghu.core.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class WebServiceUtil {
	public static void main(String[] args) throws IOException {
		String url = "http://localhost:8080/jiong/webservice/getUserInfo";
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "王五");
		System.out.println(sendPost(url, map));
	}

	/**
	 * GET请求webservice(使用默认UTF-8编码)
	 * 
	 * @param url
	 * @return
	 */
	public static String sendGet(String url) {
		return sendGet(url, "UTF-8");
	}

	/**
	 * GET请求webservice(传入编码，为空为UTF-8)
	 * 
	 * @param url
	 * @return
	 */
	public static String sendGet(String url, String encoding) {
		String returnStr = "";
		try {
			if ("".equals(encoding)) {
				encoding = "UTF-8";
			}
			// 创建默认的httpClient实例
			HttpClient httpclient = new DefaultHttpClient();
			// 创建httpget
			HttpGet httpget = new HttpGet(url);
			// httpget.addHeader("Accept", "application/json");
			// 执行get请求
			HttpResponse response = httpclient.execute(httpget);
			// 获取响应实体
			HttpEntity entity = response.getEntity();
			// 打印响应状态
			System.out.println(response.getStatusLine());
			if (entity != null) {
				// 打印响应内容长度
				System.out.println("Response content length: " + entity.getContentLength());
				// 打印响应内容
				returnStr = EntityUtils.toString(entity, encoding);
				System.out.println("Response content: " + returnStr);
			}
			// 销毁
			EntityUtils.consume(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnStr;
	}

	/**
	 * POST请求webservice(默认编码UTF-8)
	 * 
	 * @param url
	 *            地址
	 * @param map
	 *            参数
	 * @return
	 * @throws IOException
	 */
	public static String sendPost(String url, Map<String, String> map) {
		return sendPost(url, map, "");
	}

	/**
	 * POST请求webservice
	 * 
	 * @param url
	 *            地址
	 * @param map
	 *            参数
	 * @param encoding
	 *            编码（为空就是UTF-8）
	 * @return
	 * @throws IOException
	 */
	public static String sendPost(String url, Map<String, String> map, String encoding) {
		String returnStr = "";
		try {
			if ("".equals(encoding)) {
				encoding = "UTF-8";
			}
			// 创建httpclient对象
			HttpClient client = new DefaultHttpClient();
			// 创建post方式请求对象
			HttpPost httpPost = new HttpPost(url);
			// 装填参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (map != null) {
				for (Entry<String, String> entry : map.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			// 设置参数到请求对象中
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));
			System.out.println("请求地址：" + url);
			System.out.println("请求参数：" + nvps.toString());
			// 设置header信息
			httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
			httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			// 执行请求操作，并拿到结果（同步阻塞）
			HttpResponse response = client.execute(httpPost);
			// 获取结果实体
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// 按指定编码转换结果实体为String类型
				returnStr = EntityUtils.toString(entity, encoding);
			}
			// 销毁
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnStr;
	}
}
