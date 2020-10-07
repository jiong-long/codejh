package com.cases.designPattern.flyweight;

import java.util.HashMap;
import java.util.Map;

public class SignInfoFactory {
	private static Map<String, SignInfo> pool = new HashMap<String, SignInfo>();

	public static SignInfo getSignInfo(String key) {
		SignInfo signInfo = null;
		if (!pool.containsKey(key)) {
			signInfo = new SignInfo4Pool(key);
			pool.put(key, signInfo);
			System.err.println(key + "建立对象");
		} else {
			signInfo = pool.get(key);
			System.err.println(key + "直接从池中取得");
		}
		return signInfo;
	}

}
