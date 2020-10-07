package com.cases.designPattern.dymanicProxy.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * InvocationHandler是JDK提供的动态代理接口
 * 
 * @author jinlong
 *
 */
public class GamePlayIH implements InvocationHandler {
	//被代理者
	@SuppressWarnings("rawtypes")
	Class cls = null;

	//被代理的实例
	Object obj = null;

	public GamePlayIH(Object obj) {
		this.obj = obj;
	}

	//调用被代理的方法
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = method.invoke(obj, args);
		//代理的好处，面向切面的编程
		if (method.getName().equalsIgnoreCase("login")) {
			System.out.println("有人在用我的账号登录！");
		}
		return result;
	}

}
