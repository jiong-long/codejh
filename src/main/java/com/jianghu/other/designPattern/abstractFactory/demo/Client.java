package com.jianghu.other.designPattern.abstractFactory.demo;

import com.jianghu.other.designPattern.abstractFactory.demo.impl.FemaleFactory;
import com.jianghu.other.designPattern.abstractFactory.demo.impl.MaleFactory;

public class Client {
	public static void main(String[] args) {
		//男人生成线
		HumanFactory maleHumanFactory = new MaleFactory();
		//女人生产线
		HumanFactory femaleHumanFactory = new FemaleFactory();

		Human maleHuman = maleHumanFactory.createYellowHuman();
		maleHuman.getColor();
		maleHuman.talk();
		maleHuman.getSex();

		Human femaleHumanle = femaleHumanFactory.createYellowHuman();
		femaleHumanle.getColor();
		femaleHumanle.talk();
		femaleHumanle.getSex();
	}
}
