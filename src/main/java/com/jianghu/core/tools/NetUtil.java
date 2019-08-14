package com.jianghu.core.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * 网络（IP）相关的工具类
 * 
 * @creatTime 2017年10月4日 上午12:43:10
 * @author jinlong
 * 
 */
public class NetUtil {
	//静态成员变量按照顺序加载
	private static Pattern URL_PATTERN = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
	private static Pattern IP_PATTERN = Pattern.compile("(\\d{1,3})[.](\\d{1,3})[.](\\d{1,3})[.](\\d{1,3})");
	public static String urlString = "http://2018.ip138.com/ic.asp";// 获取IP的网站
	public static String LOCAL_IP = getIpFromLocation();// 内网IP
	public static String REMOTE_IP = getIpFromUrl();// 外网IP

	public static void main(String[] args) throws Exception {
		System.out.println(REMOTE_IP);
	}

	/**
	 * 从cmd中获取ip(内网)
	 * 
	 * 不同的机器可能会在后面拼接上其他字符串需要截取
	 * 
	 * @creatTime 2017年10月23日 下午10:47:43
	 * @author jinlong
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static String getIpFromCmd() throws IOException, InterruptedException {
		String ip = "";
		String s1 = "ipconfig /all";
		Process process = Runtime.getRuntime().exec(s1);
		BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
		String nextLine;
		for (String line = bufferedreader.readLine(); line != null; line = nextLine) {
			nextLine = bufferedreader.readLine();
			if (line.indexOf("IPv4") <= 0) {
				continue;
			}
			int i = line.indexOf(":") + 1;
			ip = line.substring(i);
			break;
		}

		bufferedreader.close();
		process.waitFor();
		return ip.trim();
	}

	/**
	 * 获得外网IP(通过第三方网站，读取url内容，匹配获取)
	 * 
	 * 如果访问次数过多，需要验证才能正常访问
	 * 
	 * 该方法只针对一个网站，现使用getIpFromUrl()
	 * 
	 * @return 外网IP
	 * @throws MalformedURLException
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	@Deprecated
	public static String getOutIpFromUrl() {
		String chinaz = "http://ip.chinaz.com";
		StringBuilder inputLine = new StringBuilder();
		BufferedReader in = null;
		try {
			URL url = new URL(chinaz);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			String read = "";
			while ((read = in.readLine()) != null) {
				inputLine.append(read + "\r\n");
			}
		} catch (Exception e) {
			Log.error(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					Log.error(e);
				}
			}
		}

		// IP的正则表达式？
		Matcher m = URL_PATTERN.matcher(inputLine.toString());
		String ip = "";
		if (m.find()) {
			String ipstr = m.group(1);
			ip = ipstr;
		}
		return ip;
	}

	/**
	 * 通过网站url获取IP(外网)
	 * 
	 * @param urlString
	 * @return
	 */
	public static String getIpFromUrl() {
		String returnedhtml = getWebContent();
		if (returnedhtml == null) {
			return "";
		}
		return parse(returnedhtml);
	}

	/**
	 * 通过URL获取网站的内容
	 * 
	 * @return
	 */
	private static String getWebContent() {
		// 输入流
		InputStream in = null;
		// 到外网提供者的Http连接
		HttpURLConnection httpConn = null;
		StringBuffer sb = new StringBuffer();
		try {
			// 打开连接
			URL url = new URL(urlString);
			httpConn = (HttpURLConnection) url.openConnection();
			// 连接设置
			HttpURLConnection.setFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
			// 获取连接的输入流
			BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
			String strContent = null;
			while((strContent = br.readLine()) != null){
				sb.append(strContent);
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				httpConn.disconnect();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		// 出现异常则返回空
		return null;
	}

	/**
	 * 使用正则表达式解析返回的HTML文本,得到本机外网地址
	 * 
	 * @param html
	 */
	private static String parse(String html) {
		Matcher matcher = IP_PATTERN.matcher(html);
		String ip = "";
		while (matcher.find()) {
			ip = matcher.group(0);
		}
		return ip;
	}

	/**
	 * 获取本机的IP(内网)
	 * 
	 * @creatTime 2017年10月4日 上午12:35:44
	 * @author jinlong
	 * @return
	 * @throws UnknownHostException
	 */
	public static String getIpFromLocation() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			Log.error(e);
		}
		return "";
	}

	/**
	 * 从request中获取客户端IP（外网）
	 * 
	 * @creatTime 2017年10月3日 下午11:56:36
	 * @author jinlong
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getIpFromRequest(HttpServletRequest request) throws Exception {
		String ip = request.getHeader("x-forwarded-for");
		if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {// 如果是本机
				ip = REMOTE_IP;
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ip != null && ip.length() > 15) { // "***.***.***.***".length() = 15
			if (ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}
		return ip;
	}
}
