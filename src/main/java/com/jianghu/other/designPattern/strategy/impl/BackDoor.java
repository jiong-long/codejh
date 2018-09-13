package com.jianghu.other.designPattern.strategy.impl;

import com.jianghu.other.designPattern.strategy.IStrategy;

public class BackDoor implements IStrategy {

	@Override
	public void operate() {
		System.out.println("找乔国老帮忙，让吴国太给孙权施加压力。");
	}

}
