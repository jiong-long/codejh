package com.jianghu.other.designPattern.abstractFactory.general.impl;

import com.jianghu.other.designPattern.abstractFactory.general.AbstractCreator;
import com.jianghu.other.designPattern.abstractFactory.general.AbstractProduceA;

public class Creator1 extends AbstractCreator {

	@Override
	public AbstractProduceA createProduceA() {
		return new ProduceA1();
	}

}
