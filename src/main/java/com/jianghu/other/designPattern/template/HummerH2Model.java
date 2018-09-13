package com.jianghu.other.designPattern.template;

public class HummerH2Model extends HummerModel {

	@Override
	protected void start() {
		System.out.println("悍马H2发动……");
	}

	@Override
	protected void stop() {
		System.out.println("悍马H2停止……");
	}

	@Override
	protected void alarm() {
		System.out.println("悍马H2鸣笛……");
	}

	@Override
	protected void engineBoom() {
		System.out.println("悍马H2引擎声音是这样的……");
	}

	protected boolean isAlarm() {
		return false;
	}
}
