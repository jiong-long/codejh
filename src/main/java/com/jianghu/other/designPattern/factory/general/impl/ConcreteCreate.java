package com.jianghu.other.designPattern.factory.general.impl;

import com.jianghu.other.designPattern.factory.general.Creator;
import com.jianghu.other.designPattern.factory.general.Product;

public class ConcreteCreate extends Creator {

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Product> T createProduct(Class<T> c) {
		Product product = null;
		try {
			product = (T) Class.forName(c.getName()).newInstance();
		} catch (Exception e) {
			//异常处理
		}
		return (T) product;
	}

}
