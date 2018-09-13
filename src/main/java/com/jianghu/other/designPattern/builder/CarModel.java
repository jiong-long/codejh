package com.jianghu.other.designPattern.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 车模型
 * 
 * @author jinlong
 *
 */
public abstract class CarModel {
	private List<String> sequence = new ArrayList<String>();

	protected abstract void start();

	protected abstract void stop();

	protected abstract void alarm();

	protected abstract void engineBoom();

	//按照规定的顺序执行
	public final void run() {
		for (String actionName : sequence) {
			if ("start".equalsIgnoreCase(actionName)) {
				this.start();
			} else if ("stop".equalsIgnoreCase(actionName)) {
				this.stop();
			} else if ("alarm".equalsIgnoreCase(actionName)) {
				this.alarm();
			} else if ("engine boom".equalsIgnoreCase(actionName)) {
				this.engineBoom();
			}
		}
	}

	public final void setSequence(List<String> sequence) {
		this.sequence = sequence;
	}
}
