package com.jianghu.other.designPattern.builder.impl;

import java.util.List;

import com.jianghu.other.designPattern.builder.CarBuilder;
import com.jianghu.other.designPattern.builder.CarModel;

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
