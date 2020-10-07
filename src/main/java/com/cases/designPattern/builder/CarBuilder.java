package com.cases.designPattern.builder;

import java.util.List;

/**
 * 建造者，负责设计车的顺序与获取车对象CarModel
 * 
 * @author jinlong
 *
 */
public abstract class CarBuilder {
	public abstract void setSequence(List<String> sequence);

	public abstract CarModel getCarModel();
}
