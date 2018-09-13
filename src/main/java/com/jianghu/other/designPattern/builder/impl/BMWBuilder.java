package com.jianghu.other.designPattern.builder.impl;

import java.util.List;

import com.jianghu.other.designPattern.builder.CarBuilder;
import com.jianghu.other.designPattern.builder.CarModel;

public class BMWBuilder extends CarBuilder {
	private BMWModel bmw = new BMWModel();

	@Override
	public void setSequence(List<String> sequence) {
		this.bmw.setSequence(sequence);
	}

	@Override
	public CarModel getCarModel() {
		return this.bmw;
	}

}
