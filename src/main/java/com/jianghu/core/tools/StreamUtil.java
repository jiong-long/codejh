package com.jianghu.core.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 流相关的工具类
 * 
 * @author wangjinlong
 * 
 * @creatTime 2017年5月21日 下午9:52:57
 */
public class StreamUtil {
	/**
	 * inputStream转outputStream
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年5月21日 下午9:54:53
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream parse(InputStream inputStream) throws Exception {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		int ch;
		while ((ch = inputStream.read()) != -1) {
			swapStream.write(ch);
		}
		return swapStream;
	}

	/**
	 * outputStream转inputStream
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年5月21日 下午9:55:20
	 * @param out
	 * @return
	 * @throws Exception
	 */
	public ByteArrayInputStream parse(OutputStream outputStream) throws Exception {
		ByteArrayOutputStream baos = (ByteArrayOutputStream) outputStream;
		ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
		return swapStream;
	}

	/**
	 * inputStream转String
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年5月21日 下午9:56:14
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public String parse_String(InputStream inputStream) throws Exception {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		int ch;
		while ((ch = inputStream.read()) != -1) {
			swapStream.write(ch);
		}
		return swapStream.toString();
	}

	/**
	 * OutputStream 转 String
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年5月21日 下午9:56:54
	 * @param out
	 * @return
	 * @throws Exception
	 */
	public String parse_String(OutputStream out) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos = (ByteArrayOutputStream) out;
		ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
		return swapStream.toString();
	}

	/**
	 * String 转 inputStream
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年5月21日 下午9:57:03
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public ByteArrayInputStream parse_inputStream(String in) throws Exception {
		ByteArrayInputStream input = new ByteArrayInputStream(in.getBytes());
		return input;
	}

	/**
	 * String 转 outputStream
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年5月21日 下午9:57:13
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream parse_outputStream(String in) throws Exception {
		return parse(parse_inputStream(in));
	}
}
