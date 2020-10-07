package com.cases.designPattern.factory.general;

import com.cases.designPattern.factory.general.impl.ConcreteCreate;
import com.cases.designPattern.factory.general.impl.ConcreteProduce1;

public class Client {
	public static void main(String[] args) {
		Creator creator = new ConcreteCreate();
		Product product = creator.createProduct(ConcreteProduce1.class);
		product.method1();
		//其他业务处理
	}
}
