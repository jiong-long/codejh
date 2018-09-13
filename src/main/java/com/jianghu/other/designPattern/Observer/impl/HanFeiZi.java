package com.jianghu.other.designPattern.Observer.impl;

import java.util.Observable;

import com.jianghu.other.designPattern.Observer.IHanFeiZi;

public class HanFeiZi extends Observable implements IHanFeiZi {
	@Override
	public void haveBreakfast() {
		System.out.println("韩非子：开始吃饭了……");
		super.setChanged();
		this.notifyObservers("韩非子在吃饭");
	}

	@Override
	public void haveFun() {
		System.out.println("韩非子：开始娱乐了……");
		super.setChanged();
		this.notifyObservers("韩非子在娱乐");
	}
}
