package com.jianghu.other.designPattern.decorator.impl;

import com.jianghu.other.designPattern.decorator.SchoolReport;

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
