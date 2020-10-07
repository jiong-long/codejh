package com.cases.designPattern.factory.demo.impl;

import com.cases.designPattern.factory.demo.Human;

/**
 * 黑人
 * 
 * @author jinlong
 *
 */
public class BlackHuman implements Human {

	@Override
	public void getColor() {
		System.out.println("黑色人种的皮肤是黑色的！");
	}

	@Override
	public void talk() {
		System.out.println("黑人会说话，一般人听不懂。");
	}

}
