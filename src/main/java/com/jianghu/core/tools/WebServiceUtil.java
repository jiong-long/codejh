package com.jianghu.core.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebServiceUtil {

	public static void main(String[] args) throws IOException, JSONException {
		String url = "http://localhost:8080/jiong/webservice/getUserInfo";
		
		//Params方式
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "王五");
		System.out.println(sendPost(url, map));
		
		//Raw方式，就是拼接成普通的字符串传递
		JSONArray array = new JSONArray();
		array.put("cpmoid_txt");
		array.put("cpmoid");
		JSONObject object = new JSONObject();
		object.put("userKey", "96a5edd5-401c-4c74-be68-3bd0513d701b");
		object.put("dataSource", "ennshare_hr");
		object.put("dataTable", "hr_employee_delta_h");
		object.put("queryType", "query");
		object.put("returnFields", array);
		System.out.println(sendPost(url, object.toString(), null));
	}

	/**
	 * GET请求webservice(使用默认UTF-8编码)
	 * 
	 * @param url
	 * @return
	 */
	public static String sendGet(String url) {
		return sendGet(url, null);
	}

	/**
	 * GET请求webservice
	 * 
	* @param url
	 *            地址
	 * @param headMap
	 *            请求头信息
	 * @return
	 */
	public static String sendGet(String url, Map<String,String> headMap) {
		String returnStr = "";
		HttpEntity entity = null;
		try {
			// 创建默认的httpClient实例
			HttpClient httpclient = HttpClientBuilder.create().build();
			// 创建httpget
			HttpGet httpget = new HttpGet(url);
			// 设置header信息
			if(headMap != null && !headMap.isEmpty()) {
				Set<String> keySet = headMap.keySet();
				for (String key : keySet) {
					httpget.addHeader(key, headMap.get(key));
				}
			}	
			// 执行get请求
			HttpResponse response = httpclient.execute(httpget);
			// 获取响应实体
			entity = response.getEntity();
			if (entity != null) {
				// 打印响应内容
				returnStr = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(entity != null) {
				try {
					EntityUtils.consume(entity);
				} catch (IOException e) {
					Log.error(e);
				}
			}
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
		return sendPost(url, map, null);
	}
	
	/**
	 * POST请求webservice，raw方式 带自定义head信息的
	 * @param url 请求地址
	 * @param params 参数
	 * @param headMap 请求头信息
	 * @return
	 */
	public static String sendPost(String url, String params, Map<String,String> headMap) {
		String returnStr = "";
		HttpEntity entity = null;
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			//设置请求的状态参数 单位毫秒
		    RequestConfig requestConfig = RequestConfig.custom()
		    		.setConnectionRequestTimeout(10000)
		    		.setConnectTimeout(10000)
		    		.setSocketTimeout(10000)
		    		.build();
			// 创建httpclient对象
			client = HttpClientBuilder.create().build();
			// 创建post方式请求对象
			HttpPost httpPost = new HttpPost(url);
			// 设置参数到请求对象中
			StringEntity stringEntity = new StringEntity(params, "UTF-8");  
			httpPost.setEntity(stringEntity);
			httpPost.setConfig(requestConfig);
			//System.out.println("请求地址：" + url);
			//System.out.println("请求参数：" + params);
			// 设置header信息
			if(headMap != null && !headMap.isEmpty()) {
				Set<String> keySet = headMap.keySet();
				for (String key : keySet) {
					httpPost.setHeader(key, headMap.get(key));
				}
			}	
			// 执行请求操作，并拿到结果（同步阻塞）
			response = client.execute(httpPost);
			// 获取结果实体
			entity = response.getEntity();
			if (entity != null) {
				// 按指定编码转换结果实体为String类型
				returnStr = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(entity != null) {
				try {
					EntityUtils.consume(entity);
				} catch (IOException e) {
					Log.error(e);
				}
			}
			if(client != null) {
				try {
					client.close();
				} catch (IOException e) {
					Log.error(e);
				}
			}
			if(response != null) {
				try {
					response.close();
				} catch (IOException e) {
					Log.error(e);
				}
			}
		}
		return returnStr;
	}

	/**
	 * 调用POST接口，params方式
	 * @param url
	 * @param paramsMap
	 * @param headMap
	 * @return
	 */
	public static String sendPost(String url, Map<String, String> paramsMap, Map<String,String> headMap) {
		String returnStr = "";
		HttpEntity entity = null;
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			//设置请求的状态参数 单位毫秒
		    RequestConfig requestConfig = RequestConfig.custom()
		    		.setConnectionRequestTimeout(10000)
		    		.setConnectTimeout(10000)
		    		.setSocketTimeout(10000)
		    		.build();
			client = HttpClientBuilder.create().build();
			// 创建HttpPost对象
			HttpPost httpPost = new HttpPost(url);
			// 装填参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (paramsMap != null) {
				for (Entry<String, String> entry : paramsMap.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			httpPost.setConfig(requestConfig);
			// 设置header信息
			if(headMap != null && !headMap.isEmpty()) {
				Set<String> keySet = headMap.keySet();
				for (String key : keySet) {
					httpPost.setHeader(key, headMap.get(key));
				}
			}	
			// 执行请求操作，并拿到结果（同步阻塞）
			response = client.execute(httpPost);
			// 获取结果实体
			entity = response.getEntity();
			if (entity != null) {
				// 按指定编码转换结果实体为String类型
				returnStr = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(entity != null) {
				try {
					EntityUtils.consume(entity);
				} catch (IOException e) {
					Log.error(e);
				}
			}
			if(client != null) {
				try {
					client.close();
				} catch (IOException e) {
					Log.error(e);
				}
			}
			if(response != null) {
				try {
					response.close();
				} catch (IOException e) {
					Log.error(e);
				}
			}
		}
		return returnStr;
	}
}
