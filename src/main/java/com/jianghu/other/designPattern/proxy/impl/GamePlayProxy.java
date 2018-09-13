package com.jianghu.other.designPattern.proxy.impl;

import com.jianghu.other.designPattern.proxy.IGamePlayer;
import com.jianghu.other.designPattern.proxy.IProxy;

/**
 * 代理类
 * 
 * @author jinlong
 *
 */
public class GamePlayProxy implements IGamePlayer, IProxy {
	//被代理的对象
	private IGamePlayer gamePlayer = null;

	public GamePlayProxy(IGamePlayer gamePlayer) {
		this.gamePlayer = gamePlayer;
	}

	@Override
	public void login(String user, String password) {
		this.gamePlayer.login(user, password);
	}

	@Override
	public void killBoos() {
		this.gamePlayer.killBoos();
	}

	@Override
	public void upgrade() {
		this.gamePlayer.upgrade();
		after();
		count();
	}

	//预处理（代理类的好处，否则可以直接使用被代理类对象本身）
	public void before() {
		//do something
	}

	//善后处理
	public void after() {
		System.out.println("升了一级，加钱");
	}

	@Override
	public IGamePlayer getProxy() {
		return this;
	}

	@Override
	public void count() {
		System.out.println("升级总费用为：150元");
	}
}
