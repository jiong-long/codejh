package com.jianghu.other.designPattern.strategy.impl;

import com.jianghu.other.designPattern.strategy.IStrategy;

public class GiveGreenLight implements IStrategy {

	@Override
	public void operate() {
		System.out.println("求吴国太开绿灯，放心！");
	}

}
