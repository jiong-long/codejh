package com.cases.designPattern.iterator;

import com.cases.designPattern.iterator.impl.Project;

public class Client {
	public static void main(String[] args) {
		IProject project = new Project();
		project.add("星球大战", 10, 10000);
		project.add("星球大战2", 100, 100000);

		IProjectIterator iterator = project.iterator();
		while (iterator.hasNext()) {
			IProject project2 = iterator.next();
			System.out.println(project2.getProject());
		}
	}
}
