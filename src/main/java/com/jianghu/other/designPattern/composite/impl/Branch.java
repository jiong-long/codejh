package com.jianghu.other.designPattern.composite.impl;

import java.util.ArrayList;
import java.util.List;

import com.jianghu.other.designPattern.composite.Corp;

public class Branch extends Corp {
	private List<Corp> subordinateList = new ArrayList<Corp>();

	public Branch(String name, String position, int salary) {
		super(name, position, salary);
	}

	public void add(Corp corp) {
		this.subordinateList.add(corp);
	}

	public List<Corp> getSubordinateInfo() {
		return this.subordinateList;
	}

}
