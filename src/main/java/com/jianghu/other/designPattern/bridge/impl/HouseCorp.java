package com.jianghu.other.designPattern.bridge.impl;

import com.jianghu.other.designPattern.bridge.Corp;
import com.jianghu.other.designPattern.bridge.Product;

public class HouseCorp extends Corp {

	public HouseCorp(Product product) {
		super(product);
	}

	public void makeMoney() {
		super.makeMoney();
		System.out.println("房地产公司赚大钱了……");
	}

}
