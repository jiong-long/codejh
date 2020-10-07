package com.cases.designPattern.visitor.impl;

import com.cases.designPattern.visitor.Employee;
import com.cases.designPattern.visitor.IVisitor;

public class Visitor implements IVisitor {

	@Override
	public void visit(CommonEmployee commonEmployee) {
		System.out.println(this.getCommonEmployee(commonEmployee));
	}

	@Override
	public void visit(Manager manager) {
		System.out.println(this.getManagerInfo(manager));
	}

	private String getBaseInfo(Employee employee) {
		String info = "姓名：" + employee.getName() + "\t";
		info += "性别：" + (employee.getSex() == Employee.MALE ? "男" : "女") + "\t";
		info += "薪水：" + employee.getSalary() + "\t";
		return info;
	}

	private String getManagerInfo(Manager manager) {
		String basicInfo = getBaseInfo(manager);
		String otherInfo = "业绩：" + manager.getPerformance() + "\t";
		return basicInfo + otherInfo;
	}

	private String getCommonEmployee(CommonEmployee commonEmployee) {
		String basicInfo = getBaseInfo(commonEmployee);
		String otherInfo = "工作：" + commonEmployee.getJob() + "\t";
		return basicInfo + otherInfo;
	}

}
