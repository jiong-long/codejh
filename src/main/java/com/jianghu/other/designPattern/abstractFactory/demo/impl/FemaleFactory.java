package com.jianghu.other.designPattern.abstractFactory.demo.impl;

import com.jianghu.other.designPattern.abstractFactory.demo.Human;
import com.jianghu.other.designPattern.abstractFactory.demo.HumanFactory;

public class FemaleFactory implements HumanFactory {

	@Override
	public Human createYellowHuman() {
		return new MaleYellowHuman();
	}

}
