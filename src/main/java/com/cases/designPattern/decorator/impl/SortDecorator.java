package com.cases.designPattern.decorator.impl;

import com.cases.designPattern.decorator.Decorator;
import com.cases.designPattern.decorator.SchoolReport;

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
