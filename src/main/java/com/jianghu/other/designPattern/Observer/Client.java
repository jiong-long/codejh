package com.jianghu.other.designPattern.Observer;

import com.jianghu.other.designPattern.Observer.impl.HanFeiZi;
import com.jianghu.other.designPattern.Observer.impl.LiSi;
import com.jianghu.other.designPattern.Observer.impl.WangSi;

public class Client {
	public static void main(String[] args) throws InterruptedException {
		LiSi lisi = new LiSi();
		WangSi wangsi = new WangSi();

		HanFeiZi hanFeiZi = new HanFeiZi();
		hanFeiZi.addObserver(lisi);
		hanFeiZi.addObserver(wangsi);

		hanFeiZi.haveBreakfast();
		hanFeiZi.haveFun();
	}
}
