package com.cases.designPattern.factory.demo;

public interface AbstractHumanFactory {
	//T为所有的Human的子类
	public abstract <T extends Human> T createHuman(Class<T> c);
}
