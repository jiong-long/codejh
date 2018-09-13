package com.jianghu.other.designPattern.singleton.general;

import java.lang.reflect.Constructor;

/**
 * 单例工厂（通过反射获得一个类对象）
 * 
 * @author jinlong
 *
 */
public class SingletionFactory {
	private static Singleton singleton;
	static {
		try {
			//获得类对象
			Class<?> cl = Class.forName(Singleton.class.getName());
			//获得无参的构造函数
			Constructor<?> constructor = cl.getDeclaredConstructor();
			//设置该无参的构造函数是可访问的
			constructor.setAccessible(true);
			//产生一个实例对象
			singleton = (Singleton) constructor.newInstance();
		} catch (Exception e) {
			//异常处理
		}
	}

	/**
	 * 提供方法获取该单例对象
	 * 
	 * @return
	 */
	public static Singleton getSingletion() {
		return singleton;
	}
}
