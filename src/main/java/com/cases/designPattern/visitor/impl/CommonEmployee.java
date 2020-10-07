package com.cases.designPattern.visitor.impl;

import com.cases.designPattern.visitor.Employee;
import com.cases.designPattern.visitor.IVisitor;

public class CommonEmployee extends Employee {
	private String job;

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	protected void accept(IVisitor iVisitor) {
		iVisitor.visit(this);
	}
}
