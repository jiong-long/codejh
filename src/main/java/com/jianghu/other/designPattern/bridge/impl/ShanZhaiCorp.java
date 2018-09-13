package com.jianghu.other.designPattern.bridge.impl;

import com.jianghu.other.designPattern.bridge.Corp;
import com.jianghu.other.designPattern.bridge.Product;

public class ShanZhaiCorp extends Corp {

	public ShanZhaiCorp(Product product) {
		super(product);
	}

	public void makeMoney() {
		super.makeMoney();
		System.out.println("我赚钱呀……");
	}

}
