package com.cases.designPattern.factory.demo.impl;

import com.cases.designPattern.factory.demo.Human;

/**
 * 黄人
 * 
 * @author jinlong
 *
 */
public class YellowHuman implements Human {

	@Override
	public void getColor() {
		System.out.println("黄色人种的皮肤是黄色的！");
	}

	@Override
	public void talk() {
		System.out.println("黄色人种会说话，一般说的都是双节字。");
	}

}
