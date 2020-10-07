package com.cases.designPattern.factory.general.impl;

import com.cases.designPattern.factory.general.Creator;
import com.cases.designPattern.factory.general.Product;

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
