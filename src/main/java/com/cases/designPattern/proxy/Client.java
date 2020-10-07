package com.cases.designPattern.proxy;

import com.cases.designPattern.proxy.impl.GamePlayer;

/**
 * 代理类和被代理类要实现同一个接口，代理类中有被代理类的引用
 * 
 * @author jinlong
 *
 */
public class Client {
	public static void main(String[] args) throws Exception {
		IGamePlayer player = new GamePlayer("张三");
		IGamePlayer proxy = player.getProxy();
		proxy.login("zhangsan", "password");
		proxy.killBoos();
		proxy.upgrade();
	}
}
