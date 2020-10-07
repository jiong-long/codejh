package com.cases.designPattern.strategy.impl;

import com.cases.designPattern.strategy.IStrategy;

public class BlockEnemy implements IStrategy {

	@Override
	public void operate() {
		System.out.println("孙夫人断后，挡住追兵");
	}

}
