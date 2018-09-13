package com.jianghu.other.designPattern.singleton.general;

/**
 * 单例通用代码
 * 
 * @author jinlong
 *
 */
public class Singleton {
	//类加载时初始化单例对象
	private static final Singleton singleton = new Singleton();

	//去掉改造函数，防止产生第二个对象
	public Singleton() {
	}

	//提供方法获取到单例对象
	public static Singleton getInstance() {
		return singleton;
	}

	public static void doSomething() {
		//其他处理
	}
}
