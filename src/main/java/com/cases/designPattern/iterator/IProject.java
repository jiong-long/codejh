package com.cases.designPattern.iterator;

public interface IProject {
	public String getProject();

	public void add(String name, int num, int cost);

	public IProjectIterator iterator();
}
