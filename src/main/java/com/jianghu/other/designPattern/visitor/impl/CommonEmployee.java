package com.jianghu.other.designPattern.visitor.impl;

import com.jianghu.other.designPattern.visitor.Employee;
import com.jianghu.other.designPattern.visitor.IVisitor;

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
