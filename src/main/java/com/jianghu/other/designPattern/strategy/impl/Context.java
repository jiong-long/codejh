package com.jianghu.other.designPattern.strategy.impl;

import com.jianghu.other.designPattern.strategy.IStrategy;

public class Context {
	private IStrategy strategy;

	public Context(IStrategy strategy) {
		super();
		this.strategy = strategy;
	}

	public void operate() {
		this.strategy.operate();
	}
}
