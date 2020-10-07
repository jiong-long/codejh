package com.cases.designPattern.abstractFactory.general.impl;

import com.cases.designPattern.abstractFactory.general.AbstractCreator;
import com.cases.designPattern.abstractFactory.general.AbstractProduceA;

public class Creator2 extends AbstractCreator {

	@Override
	public AbstractProduceA createProduceA() {
		return new ProduceA2();
	}

}
