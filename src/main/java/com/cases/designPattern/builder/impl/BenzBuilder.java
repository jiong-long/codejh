package com.cases.designPattern.builder.impl;

import java.util.List;

import com.cases.designPattern.builder.CarBuilder;
import com.cases.designPattern.builder.CarModel;

public class BenzBuilder extends CarBuilder {
	private BenzModel benz = new BenzModel();

	@Override
	public void setSequence(List<String> sequence) {
		this.benz.setSequence(sequence);
	}

	@Override
	public CarModel getCarModel() {
		return this.benz;
	}

}
