package com.jianghu.other.designPattern.abstractFactory.general;

import com.jianghu.other.designPattern.abstractFactory.general.impl.Creator1;
import com.jianghu.other.designPattern.abstractFactory.general.impl.Creator2;

public class Client {
	public static void main(String[] args) {
		AbstractCreator creator1 = new Creator1();
		AbstractCreator creator2 = new Creator2();

		AbstractProduceA a1 = creator1.createProduceA();
		AbstractProduceA a2 = creator2.createProduceA();

		a1.doSomething();

		a2.doSomething();
	}
}
