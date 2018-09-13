package com.jianghu.other.designPattern.factory.simple;

import com.jianghu.other.designPattern.factory.general.Product;
import com.jianghu.other.designPattern.factory.general.impl.ConcreteProduce1;

public class Client {
	public static void main(String[] args) {
		Product product = HumanFactory.createProduct(ConcreteProduce1.class);
		product.method1();
		//其他业务处理
	}
}
