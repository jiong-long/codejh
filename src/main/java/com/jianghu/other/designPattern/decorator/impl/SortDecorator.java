package com.jianghu.other.designPattern.decorator.impl;

import com.jianghu.other.designPattern.decorator.Decorator;
import com.jianghu.other.designPattern.decorator.SchoolReport;

public class SortDecorator extends Decorator {

	public SortDecorator(SchoolReport report) {
		super(report);
	}

	private void reportSort() {
		System.out.println("我的排名是第38名……");
	}

	@Override
	public void report() {
		super.report();
		reportSort();
	}
}
