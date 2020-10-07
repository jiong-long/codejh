package com.jianghu.dao.redis.Tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.common.Log;

/**
 * 对象序列化与反序列化工具
 * 
 * @creatTime 2018年10月25日 上午12:01:58
 * @author jinlong
 */
public class SerializeUtil {
	/**
	 * 序列化对象
	 * 
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			//序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			Log.error(e);
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					Log.error(e);
				}
			}
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					Log.error(e);
				}
			}
		}
		return null;
	}

	/**
	 * 反序列化对象
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			//反序列化
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			Log.error(e);
		} finally {
			if (bais != null) {
				try {
					bais.close();
				} catch (IOException e) {
					Log.error(e);
				}
			}
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					Log.error(e);
				}
			}
		}
		return null;
	}

}
