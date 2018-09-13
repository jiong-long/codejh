package com.jianghu.other.designPattern.visitor;

public abstract class Employee {
	public final static int MALE = 0;//男
	public final static int FEMALE = 1;//女

	private String name;

	private String salary;

	private int sex;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	protected abstract void accept(IVisitor iVisitor);
}
