package com.jianghu.core.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class WebServiceUtil {
	//编码方式
	private static final String CODE_TYPE = "UTF-8";

	//填充类型
	private static final String AES_TYPE = "AES/ECB/PKCS5Padding";

	//私钥
	private static final String AES_KEY = "QWERTYUIOPASDFGH";//16位
	//AES固定格式为128/192/256 bits 即：16/24/32 Bytes. DES固定格式为128bits 即8Bytes

	public static void main(String[] args) throws IOException {
		String url = "http://localhost:8080/jiong/webservice/getUserInfo";
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "王五");
		//解密
		System.out.println(sendPost(url, map));
	}

	/**
	 * GET请求webservice(使用默认UTF-8编码)
	 * 
	 * @param url
	 * @return
	 */
	public static String sendGet(String url) {
		return sendGet(url, CODE_TYPE);
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
				encoding = CODE_TYPE;
			}
			// 创建默认的httpClient实例
			//HttpClient httpclient = new DefaultHttpClient();
			HttpClient httpclient = HttpClientBuilder.create().build();
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
				encoding = CODE_TYPE;
			}
			// 创建httpclient对象
			//HttpClient client = new DefaultHttpClient();
			HttpClient client = HttpClientBuilder.create().build();
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
		return WebServiceUtil.decrypt(returnStr);
	}

	/**
	 * 加密
	 * 
	 * @param text
	 * @return
	 */
	public static String encrypt(String text) {
		//加密方式： AES128(CBC/PKCS5Padding)
		try {
			//两个参数，第一个为私钥字节数组，第二个为加密方式AES或者DES
			SecretKeySpec key = new SecretKeySpec(AES_KEY.getBytes(), "AES");
			//实例化加密类，参数为加密方式
			Cipher cipher = Cipher.getInstance(AES_TYPE);
			//初始化，此方法可以采用三种方式，按加密算法要求来添加。
			//1、无第三个参数
			//2、第三个参数为SecureRandom random = new SecureRandom();中random对象，随机数（AES不可采用这种方式）
			//3、采用此代码中的IvParameterSpec
			//加密时使用ENCRYPT_MODE，解密时使用DECRYPT_MODE
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] excrypteData = cipher.doFinal(text.getBytes(CODE_TYPE));

			return new BASE64Encoder().encode(excrypteData);
		} catch (Exception e) {
			Log.error(e);
		}
		return "";
	}

	/**
	 * 解密
	 * 
	 * @param text
	 * @return
	 */
	public static String decrypt(String text) {
		try {
			byte[] byteArr = new BASE64Decoder().decodeBuffer(text);
			SecretKeySpec key = new SecretKeySpec(AES_KEY.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance(AES_TYPE);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decrypteData = cipher.doFinal(byteArr);

			return new String(decrypteData, CODE_TYPE);
		} catch (Exception e) {
			Log.error(e);
		}
		return "";
	}
}
