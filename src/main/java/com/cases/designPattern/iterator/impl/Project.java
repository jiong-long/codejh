package com.cases.designPattern.iterator.impl;

import java.util.ArrayList;
import java.util.List;

import com.cases.designPattern.iterator.IProject;
import com.cases.designPattern.iterator.IProjectIterator;

public class Project implements IProject {
	private List<IProject> projectList = new ArrayList<IProject>();

	public Project() {
		super();
	}

	private String name = "";

	private int num = 0;

	private int cost = 0;

	public Project(String name, int num, int cost) {
		super();
		this.name = name;
		this.num = num;
		this.cost = cost;
	}

	@Override
	public String getProject() {
		String info = "";
		info += "项目名称是：" + this.name;
		info += "\t项目人数为：" + this.num;
		info += "\t项目费用为：" + this.cost;
		return info;
	}

	@Override
	public void add(String name, int num, int cost) {
		this.projectList.add(new Project(name, num, cost));
	}

	@Override
	public IProjectIterator iterator() {
		return new ProjectIterator(this.projectList);
	}

}
