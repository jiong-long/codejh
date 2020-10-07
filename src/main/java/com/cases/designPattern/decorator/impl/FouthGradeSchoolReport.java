package com.cases.designPattern.decorator.impl;

import com.cases.designPattern.decorator.SchoolReport;

public class FouthGradeSchoolReport extends SchoolReport {

	@Override
	public void report() {
		super.report();
	}

	@Override
	public void sign(String name) {
		System.out.println("家长签名为：" + name);
	}

}
