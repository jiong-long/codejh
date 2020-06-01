package com.jianghu.core.tools;

import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密算法
 * 
 * @creatTime 2019年2月26日 下午11:22:40
 * @author jinlong
 */
public class EncryptUtil {
	//编码方式
	private static final String CODE_TYPE = "UTF-8";

	//填充类型
	private static final String AES_TYPE = "AES/ECB/PKCS5Padding";

	//私钥
	private static final String AES_KEY = "KEY";//16位
	//AES固定格式为128/192/256 bits 即：16/24/32 Bytes. DES固定格式为128bits 即8Bytes

	public static void main(String[] args) throws IOException {
		System.out.println(encrypt("12345678"));
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
