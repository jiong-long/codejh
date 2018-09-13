package com.jianghu.other.designPattern.proxy;

public interface IGamePlayer {
	public void login(String user, String password);

	public void killBoos();

	public void upgrade();

	//回去代理类对象
	public IGamePlayer getProxy();
}
