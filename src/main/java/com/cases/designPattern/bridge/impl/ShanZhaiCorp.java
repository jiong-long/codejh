package com.cases.designPattern.bridge.impl;

import com.cases.designPattern.bridge.Corp;
import com.cases.designPattern.bridge.Product;

public class ShanZhaiCorp extends Corp {

	public ShanZhaiCorp(Product product) {
		super(product);
	}

	@Override
	public void makeMoney() {
		super.makeMoney();
		System.out.println("我赚钱呀……");
	}

}
