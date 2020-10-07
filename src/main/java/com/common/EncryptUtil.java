package com.common;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
	private static final String AES_KEY = "1111111111111111";//16位
	//AES固定格式为128/192/256 bits 即：16/24/32 Bytes. DES固定格式为128bits 即8Bytes

	public static void main(String[] args) throws IOException {
		System.out.println(decrypt("glHMwYIus3Nr8/H8ypZFWQ=="));
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
	
	/**
	 * 
	 * 为了防止API调用过程中被黑客恶意篡改,调用任何一个API都需要携带数字签名,服务端会对签名进行校验,签名不合法的请求将会被拒绝,目前支持的签名算法为SHA1,签名的大致过程如下:

		1.对所有API请求参数（除去sign参数本身），根据参数名称的ASCII码表的顺序排序。如:bad=2,bac=1,cba=3,排序后的顺序是bac=1,bad=2,cba=3。
		2.将排序好的参数排在一起,根据上面的示例得到的排序后的结果:bac1bad2cba3。
		3.把拼装好的字符串用uft-8编码,需要在拼装后的字符串前后都加上应用的appSecret在进行SHA1摘要,例如应用的appSecret为secret,进行签名计算:SHA1(secretbac1bad2cba3secret)
		4.将摘要得到的字节流转化成16进制,例如将上面的签名摘要转换后的结果为:5F7DEFBFD29BDB0CEF0FBD200AB780084CE86ADC

		说明:SHA1为安全哈希算法,主要用于数字签名标准(DSS)中的签名算法(DSA),SHA1会产生一个160位的消息摘要,用16进制表示,一个16进制的字符能表示4个位,所以签名后的长度固定为40个16进制字符.
	 * 
     * 对paramValues进行签名，其中ignoreParamNames这些参数不参与签名
     * @param paramValues
     * @param ignoreParamNames
     * @param secret
     * @return
	 * @throws IOException 
     */
    public static String sign(Map<String, String> paramValues, List<String> ignoreParamNames, String secret) throws IOException {
        try {
            StringBuilder sb = new StringBuilder();
            List<String> paramNames = new ArrayList<String>(paramValues.size());
            paramNames.addAll(paramValues.keySet());
            if(ignoreParamNames != null && ignoreParamNames.size() > 0){
                for (String ignoreParamName : ignoreParamNames) {
                    paramNames.remove(ignoreParamName);
                }
            }
            Collections.sort(paramNames);

            sb.append(secret);
            for (String paramName : paramNames) {
                sb.append(paramName).append(paramValues.get(paramName));
            }
            sb.append(secret);
            byte[] sha1Digest = getSHA1Digest(sb.toString());
            return byte2hex(sha1Digest);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
    
    private static byte[] getSHA1Digest(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            bytes = md.digest(data.getBytes("UTF-8"));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse);
        }
        return bytes;
    }
    
    /**
     * 二进制转十六进制字符串
     *
     * @param bytes
     * @return
     */
    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }
}
