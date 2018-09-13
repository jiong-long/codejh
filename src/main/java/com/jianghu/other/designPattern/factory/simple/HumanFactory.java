package com.jianghu.other.designPattern.factory.simple;

import com.jianghu.other.designPattern.factory.general.Product;

public class HumanFactory {
	/**
	 * 变为静态方法直接调用
	 * 
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Product> T createProduct(Class<T> c) {
		Product product = null;
		try {
			product = (T) Class.forName(c.getName()).newInstance();
		} catch (Exception e) {
			//异常处理
		}
		return (T) product;
	}
}
