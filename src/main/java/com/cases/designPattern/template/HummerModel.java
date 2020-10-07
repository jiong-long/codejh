package com.cases.designPattern.template;

public abstract class HummerModel {
	protected abstract void start();

	protected abstract void stop();

	protected abstract void alarm();

	protected abstract void engineBoom();

	//相同的方法提取到父类就是模板模式
	public void run() {
		this.start();
		this.engineBoom();
		if (this.isAlarm()) {
			this.alarm();
		}
		this.stop();
	}

	protected boolean isAlarm() {
		return true;
	}
}
