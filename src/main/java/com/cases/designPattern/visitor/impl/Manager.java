package com.cases.designPattern.visitor.impl;

import com.cases.designPattern.visitor.Employee;
import com.cases.designPattern.visitor.IVisitor;

public class Manager extends Employee {
	private String performance;

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	@Override
	protected void accept(IVisitor iVisitor) {
		iVisitor.visit(this);
	}
}
