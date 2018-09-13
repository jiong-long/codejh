package com.jianghu.other.designPattern.proxy.impl;

import com.jianghu.other.designPattern.proxy.IGamePlayer;

public class GamePlayer implements IGamePlayer {

	private String name = "";

	//代理对象
	private IGamePlayer proxy = null;

	//构造函数限制谁能创建对象，并传递姓名
	public GamePlayer(String name) throws Exception {
		this.name = name;
	}

	@Override
	public void login(String user, String password) {
		if (isProxy()) {
			System.out.println("登录名为" + user + "的用户" + this.name + "登录成功！");
		} else {
			System.out.println("强制代理，只能使用代理类访问");
		}
	}

	@Override
	public void killBoos() {
		if (isProxy()) {
			System.out.println(this.name + "在打怪！");
		} else {
			System.out.println("强制代理，只能使用代理类访问");
		}
	}

	@Override
	public void upgrade() {
		if (isProxy()) {
			System.out.println(this.name + "又升了一级！");
		} else {
			System.out.println("强制代理，只能使用代理类访问");
		}
	}

	@Override
	public IGamePlayer getProxy() {
		proxy = new GamePlayProxy(this);
		return proxy;
	}

	//判断是否被代理
	public boolean isProxy() {
		if (this.proxy == null) {
			return false;
		} else {
			return true;
		}
	}

}
