package com.cases.designPattern.factory.demo;

import com.cases.designPattern.factory.demo.impl.BlackHuman;
import com.cases.designPattern.factory.demo.impl.HumanFactory;
import com.cases.designPattern.factory.demo.impl.WhiteHuman;
import com.cases.designPattern.factory.demo.impl.YellowHuman;

public class Client {
	public static void main(String[] args) {
		AbstractHumanFactory yinYanglu = new HumanFactory();
		System.out.println("--造出的第一批人是白色人种--");
		Human whiteHuman = yinYanglu.createHuman(WhiteHuman.class);
		whiteHuman.getColor();
		whiteHuman.talk();

		System.out.println("--造出的第二批人是黑色人种--");
		Human blackHuman = yinYanglu.createHuman(BlackHuman.class);
		blackHuman.getColor();
		blackHuman.talk();

		System.out.println("--造出的第三批人是黄色人种--");
		Human yellowHuman = yinYanglu.createHuman(YellowHuman.class);
		yellowHuman.getColor();
		yellowHuman.talk();
	}
}
