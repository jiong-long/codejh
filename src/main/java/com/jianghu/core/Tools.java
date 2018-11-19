package com.jianghu.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;

import flexjson.JSONSerializer;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 工具类
 * 
 * @author jinlong
 * 
 */
public class Tools {
	public static void main(String[] args) throws Exception {

	}

	/**
	 * 获取输入字符的编码
	 * 
	 * @param str
	 *            输入字符
	 * @return 返回编码
	 */
	public static String getEncoding(String str) {
		try {
			String encode = "UTF-8";
			if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是UTF-8编码
				return encode;
			}
			encode = "GBK";
			if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是GBK
				return encode;
			}
			encode = "GB2312";
			if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是GB2312
				return encode;
			}
			encode = "ISO-8859-1";
			if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是ISO-8859-1
				return encode;
			}
		} catch (Exception e) {
		}
		return ""; // 到这一步，你就应该检查是不是其他编码啦
	}

	/**
	 * 获取部署的应用名称
	 * 
	 * @creatTime 2017年11月2日 下午11:39:35
	 * @author jinlong
	 * @param request
	 * @return
	 */
	public static String getWebPath(HttpServletRequest request) {
		String contextPath = request.getContextPath().equals("/") ? "" : request.getContextPath();
		String url = "http://" + request.getServerName();
		if ("80".equals(request.getServerPort() + ""))
			url = url + ":" + request.getServerPort() + contextPath;
		else {
			url = url + contextPath;
		}
		return url;
	}

	/**
	 * 从request中读取图片并保存
	 * 
	 * @creatTime 2017年10月26日 下午10:22:03
	 * @author jinlong
	 * @param request
	 * @return 正常返回文件的路径，如果以1开头则返回异常信息
	 */
	public static String uploadFile(HttpServletRequest request) {
		// 返回的文件路径
		String returnFileUrl = "";
		// 临时文件目录
		String tempPath = "E:\\360downloads\\temp";
		// 文件保存目录
		String savePath = request.getSession().getServletContext().getRealPath("/images/temp") + "\\";
		// 判断是否为文件上传
		if (ServletFileUpload.isMultipartContent(request)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置缓冲区大小
			factory.setSizeThreshold(1024 * 1024);
			// 文件临时保存路径
			File file = new File(tempPath);
			// 设置临时文件目录
			factory.setRepository(file);
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			// 解决中文附件名乱码的问题
			fileUpload.setHeaderEncoding("utf-8");
			try {
				@SuppressWarnings("unchecked")
				List<FileItem> list = fileUpload.parseRequest(request);
				if (list == null) {
					return "1没有上传文件";
				}
				for (FileItem fileItem : list) {
					if (fileItem.isFormField()) {// 不是文件
						String name = fileItem.getFieldName();
						String value = fileItem.getString("utf-8");
						System.out.println(name + "---------" + value);
					} else {// 是文件
						String filename = fileItem.getName();
						// 部分浏览器此处显示的是全路径
						if (filename.contains("\\")) {
							filename = filename.substring(filename.lastIndexOf("\\") + 1);
						}
						System.out.println(filename);
						// 文件保存在磁盘上
						String realpath = savePath + filename;
						// 最后一个为文件，不去掉会创建为文件夹
						String createToPath = realpath.substring(0, realpath.lastIndexOf("\\"));
						File toPathFile = new File(createToPath);
						if (!toPathFile.exists()) {// 如果保存的目录不存在，就创建目录
							toPathFile.mkdirs();
						}
						System.out.println(realpath);
						// 流的操作
						InputStream stream = new BufferedInputStream(fileItem.getInputStream());
						OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(realpath));
						int temp;
						while ((temp = stream.read()) != -1) {
							outputStream.write(temp);
						}
						outputStream.flush();
						stream.close();
						outputStream.close();
						// 删除产生的临时文件,必须先关闭流
						fileItem.delete();
						// 所有操作完成后设置文件的返回路径
						returnFileUrl = realpath;
					}
				}
			} catch (Exception e) {
				return "1" + e.getMessage();
			}
		}
		return returnFileUrl;
	}

	/**
	 * 将null转化为""
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @return 为空时转化为""，否则本身
	 */
	public static String null2Empty(String str) {
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}

	/**
	 * 将空对象转化为""
	 * 
	 * @param obj
	 *            需要判断的对象
	 * @return ""或者对象本身
	 */
	public static Object object2Empty(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj;
		}
	}

	/**
	 * 使用md5的算法进行加密
	 * 
	 * @param plainText
	 *            加密原文
	 * @return 加密密文
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		return new BigInteger(1, secretBytes).toString(16);
	}

	/**
	 * 传入JSON格式的对象返回到前台
	 * 
	 * @creatTime 2016年10月12日 下午10:11:26
	 * @author jinlong
	 * @param result
	 * @throws IOException
	 */
	public static void returnJSONtoPage(Object result) throws IOException {
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(result.toString());
	}

	/**
	 * 将对象序列化为JSON并返回前台
	 * 
	 * @param object
	 *            需要序列化的对象
	 * @throws IOException
	 */
	public static void serializerObject(Object object) throws IOException {
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("*.class");

		String result = serializer.deepSerialize(object);

		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(result);
	}

	/**
	 * 将对象序列化为JSON并返回前台(排除某些不需要的字段),尽量使用Include
	 * 
	 * @param object
	 *            需要序列化的对象
	 * @param fields
	 *            不需要的字段 样式：String[] fields1 = { "*.id", "*.name"};
	 * @throws IOException
	 */
	public static void serializerObjectExclude(Object object, String[] fields) throws IOException {
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("*.class");
		serializer.exclude(fields);

		String result = serializer.deepSerialize(object);// 深度序列化

		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(result);
	}

	/**
	 * 将对象序列化为JSON并返回前台(只需要某些字段)
	 * 
	 * @param object
	 *            需要序列化的对象
	 * @param fields
	 *            需要的字段 样式：String[] fields1 = { "*.id", "*.name"};
	 * @throws IOException
	 */
	public static void serializerObjectInclude(Object object, String[] fields) throws IOException {
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("*.class");
		serializer.include(fields);

		String result = serializer.deepSerialize(object);// 深度序列化

		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(result);
	}

	/**
	 * 获得一个随机字符串
	 * 
	 * @return 随机字符串
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	/**
	 * 判断一个对象是否为空
	 * 
	 * @creatTime 2016年10月23日 下午7:26:13
	 * @author jinlong
	 * @param obj
	 * @return
	 */
	public static Boolean isEmpty(Object obj) {
		if (obj == null || "".equals(obj) || "null".equals(obj)) {
			return true;
		}
		return false;
	}

	/**
	 * 查找指定名称的cookie是否存在
	 * 
	 * @creatTime 2017年5月14日 下午2:57:47
	 * @author jinlong
	 * @param cookies
	 * @param string
	 * @return
	 */
	public static Cookie findCookieByName(Cookie[] cookies, String string) {
		if (cookies == null) {
			return null;
		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(string)) {
					return cookie;
				} else {
					return null;
				}
			}
		}
		return null;
	}

	/**
	 * 目录分散算法
	 * 
	 * 通过文件名获取hashCode,取得一二级目录（甚至更多）
	 * 
	 * @creatTime 2017年6月4日 下午12:10:22
	 * @author jinlong
	 * @param uuidFileName
	 * @return
	 */
	public static String generateRandomDir(String fileName) {
		int hashCode = fileName.hashCode();
		// 一级目录
		int d1 = hashCode & 0xf;
		// 二级目录
		int d2 = (hashCode >> 4) & 0xf;
		return "\\" + d1 + "\\" + d2 + "\\" + fileName;
	}

	/**
	 * 将字符串中的中文替换为拼音
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年6月11日 上午12:42:00
	 * @param input
	 *            需要转换的中文，可以包含非中文
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String zhongwen2pinyin(String input) throws BadHanyuPinyinOutputFormatCombination {
		char[] inputChar = input.toCharArray();

		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);// 大小写
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不需要音调

		String returnStr = "";
		for (int i = 0; i < inputChar.length; i++) {
			// 判断是否为汉字字符
			if (Character.toString(inputChar[i]).matches("[\\u4E00-\\u9FA5]+")) {
				returnStr += PinyinHelper.toHanyuPinyinStringArray(inputChar[i], format)[0];
			} else {
				returnStr += Character.toString(inputChar[i]);
			}
		}
		return returnStr;
	}

	/**
	 * 获得数据库连接，不带参数，则不需要事务
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年7月2日 上午11:40:13
	 * @return
	 * @throws Exception
	 */
	public static Connection getDatabase() throws Exception {
		return getDatabase(false);
	}

	/**
	 * 获得数据库连接，支持事务
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年7月2日 上午11:39:07
	 * @param autoCommit
	 * @return
	 * @throws Exception
	 */
	public static Connection getDatabase(boolean autoCommit) throws Exception {
		Connection connection = Database.getconnection();
		connection.setAutoCommit(autoCommit);
		return connection;
	}

	/**
	 * 判断该字符串是否是数字类型的
	 * 
	 * 在使用正则表达式时，利用好其预编译功能，可以有效加快正则匹配速度。
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年7月27日 下午11:25:27
	 * @param str
	 * @return
	 */
	private static Pattern NUMBER_PATTERN = Pattern.compile("-?[0-9]+.?[0-9]+");

	public static boolean stringIsNumber(String str) {
		Matcher isNum = NUMBER_PATTERN.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static Long object2Long(Object obj) {
		if (obj == null || "".equals(obj)) {
			obj = "0";
		}
		return Long.parseLong((String) obj);
	}
}
