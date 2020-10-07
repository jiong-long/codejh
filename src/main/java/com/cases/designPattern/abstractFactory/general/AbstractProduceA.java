package com.cases.designPattern.abstractFactory.general;

public abstract class AbstractProduceA {
	public void shareMethod() {
		System.out.println("共有方法");
	}

	//相同方法，不同实现
	public abstract void doSomething();
}
