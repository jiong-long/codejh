package com.jianghu.other.designPattern.bridge.impl;

import com.jianghu.other.designPattern.bridge.Product;

public class House extends Product {

	@Override
	public void beProducted() {
		System.out.println("生产出的房子是这样的……");
	}

	@Override
	public void beSelled() {
		System.out.println("生产出的房子卖出去了……");
	}

}
