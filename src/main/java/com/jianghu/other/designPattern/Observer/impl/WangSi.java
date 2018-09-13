package com.jianghu.other.designPattern.Observer.impl;

import java.util.Observable;
import java.util.Observer;

public class WangSi implements Observer {

	private void reportToQinShiHuang(String reportContext) {
		System.out.println("王斯：报告，王老板！韩非子有活动了-->" + reportContext);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("王斯：观察到韩非子活动，开始向老板汇报了……");
		this.reportToQinShiHuang(arg.toString());
		System.out.println("王斯：回报完毕……");
	}
}
