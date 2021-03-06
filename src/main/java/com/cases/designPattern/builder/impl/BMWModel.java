package com.cases.designPattern.builder.impl;

import com.cases.designPattern.builder.CarModel;

public class BMWModel extends CarModel {

	@Override
	protected void start() {
		System.out.println("宝马车发动……");
	}

	@Override
	protected void stop() {
		System.out.println("宝马车停止……");
	}

	@Override
	protected void alarm() {
		System.out.println("宝马车鸣笛……");
	}

	@Override
	protected void engineBoom() {
		System.out.println("宝马车引擎声音是这样的……");
	}
}
