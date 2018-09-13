package com.jianghu.other.designPattern.Observer.impl;

import java.util.Observable;
import java.util.Observer;

public class LiSi implements Observer {

	private void reportToQinShiHuang(String reportContext) {
		System.out.println("李斯：报告，秦老板！韩非子有活动了-->" + reportContext);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("李斯：观察到韩非子活动，开始向老板汇报了……");
		this.reportToQinShiHuang(arg.toString());
		System.out.println("李斯：回报完毕……");
	}
}
