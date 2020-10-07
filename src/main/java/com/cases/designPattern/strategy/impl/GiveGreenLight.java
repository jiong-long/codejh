package com.cases.designPattern.strategy.impl;

import com.cases.designPattern.strategy.IStrategy;

public class GiveGreenLight implements IStrategy {

	@Override
	public void operate() {
		System.out.println("求吴国太开绿灯，放心！");
	}

}
