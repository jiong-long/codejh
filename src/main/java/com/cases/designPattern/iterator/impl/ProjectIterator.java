package com.cases.designPattern.iterator.impl;

import java.util.ArrayList;
import java.util.List;

import com.cases.designPattern.iterator.IProject;
import com.cases.designPattern.iterator.IProjectIterator;

public class ProjectIterator implements IProjectIterator {
	private List<IProject> projectList = new ArrayList<IProject>();
	private int currentItem = 0;

	public ProjectIterator(List<IProject> projectList) {
		super();
		this.projectList = projectList;
	}

	@Override
	public boolean hasNext() {
		boolean b = true;
		if (this.currentItem >= projectList.size() || this.projectList.get(this.currentItem) == null) {
			b = false;
		}
		return b;
	}

	@Override
	public IProject next() {
		return this.projectList.get(this.currentItem++);
	}

	@Override
	public void remove() {
		//没有用到
	}

}
