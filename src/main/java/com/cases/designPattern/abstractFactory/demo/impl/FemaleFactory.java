package com.cases.designPattern.abstractFactory.demo.impl;

import com.cases.designPattern.abstractFactory.demo.Human;
import com.cases.designPattern.abstractFactory.demo.HumanFactory;

public class FemaleFactory implements HumanFactory {

	@Override
	public Human createYellowHuman() {
		return new MaleYellowHuman();
	}

}
