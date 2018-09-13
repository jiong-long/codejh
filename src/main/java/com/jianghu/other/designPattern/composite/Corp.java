package com.jianghu.other.designPattern.composite;

public abstract class Corp {
	private String name = "";

	private String position = "";

	private int salary = 0;

	public Corp(String name, String position, int salary) {
		super();
		this.name = name;
		this.position = position;
		this.salary = salary;
	}

	public String getInfo() {
		String info = "";
		info += "名称：" + this.name;
		info += "\t职位：" + this.position;
		info += "\t薪水：" + this.salary;
		return info;
	}
}
