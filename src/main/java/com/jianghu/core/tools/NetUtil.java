package com.jianghu.core.tools;

import java.io.BufferedReader;
import java.io.IOException;
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

import com.jianghu.core.func.GetLocation;

/**
 * 网络（IP）相关的工具类
 * 
 * @creatTime 2017年10月4日 上午12:43:10
 * @author jinlong
 * 
 */
public class NetUtil {
	public static void main(String[] args) throws Exception {
		System.out.println(getIpFromCmd());
	}

	/**
	 * 从cmd中获取ip相关信息
	 * 
	 * @creatTime 2017年10月23日 下午10:47:43
	 * @author jinlong
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static String getIpFromCmd() throws IOException, InterruptedException {
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
	 * @return 外网IP
	 * @throws MalformedURLException
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	public static String getOutIpFromUrl() throws Exception {
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
		} finally {
			if (in != null) {
				in.close();
			}
		}

		// IP的正则表达式？
		Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
		Matcher m = p.matcher(inputLine.toString());
		String ip = "";
		if (m.find()) {
			String ipstr = m.group(1);
			ip = ipstr;
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
	public static String getIpFromLocation() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}

	/**
	 * 从request中获取客户端实际IP
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
			if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {// 如果是本机
				ip = getOutIpFromUrl();
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
		return GetLocation.getLocationFromIp(ip);
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
			String serverIp = NetUtil.getOutIpFromUrl();
			city = GetLocation.getLocationFromIp(serverIp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}
}
