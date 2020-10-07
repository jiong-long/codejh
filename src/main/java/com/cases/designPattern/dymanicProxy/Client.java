package com.cases.designPattern.dymanicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import com.cases.designPattern.dymanicProxy.impl.GamePlayIH;
import com.cases.designPattern.dymanicProxy.impl.GamePlayer;

/**
 * 代理类和被代理类要实现同一个接口，代理类中有被代理类的引用
 * 
 * @author jinlong
 *
 */
public class Client {
	public static void main(String[] args) throws Exception {
		IGamePlayer player = new GamePlayer("张三");
		InvocationHandler handler = new GamePlayIH(player);

		//获得类的class load
		ClassLoader loader = player.getClass().getClassLoader();
		IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(loader, new Class[] { IGamePlayer.class }, handler);

		proxy.login("zhangsan", "password");
		proxy.killBoos();
		proxy.upgrade();
	}
}
