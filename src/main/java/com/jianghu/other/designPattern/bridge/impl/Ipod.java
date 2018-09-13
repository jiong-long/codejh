package com.jianghu.other.designPattern.bridge.impl;

import com.jianghu.other.designPattern.bridge.Product;

public class Ipod extends Product {

	@Override
	public void beProducted() {
		System.out.println("生产出的iPod是这样的……");
	}

	@Override
	public void beSelled() {
		System.out.println("生产出的iPod卖出去了……");
	}

}
