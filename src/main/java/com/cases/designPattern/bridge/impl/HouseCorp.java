package com.cases.designPattern.bridge.impl;

import com.cases.designPattern.bridge.Corp;
import com.cases.designPattern.bridge.Product;

public class HouseCorp extends Corp {

	public HouseCorp(Product product) {
		super(product);
	}

	@Override
	public void makeMoney() {
		super.makeMoney();
		System.out.println("房地产公司赚大钱了……");
	}

}
