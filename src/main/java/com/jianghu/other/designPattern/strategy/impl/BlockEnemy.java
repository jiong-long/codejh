package com.jianghu.other.designPattern.strategy.impl;

import com.jianghu.other.designPattern.strategy.IStrategy;

public class BlockEnemy implements IStrategy {

	@Override
	public void operate() {
		System.out.println("孙夫人断后，挡住追兵");
	}

}
