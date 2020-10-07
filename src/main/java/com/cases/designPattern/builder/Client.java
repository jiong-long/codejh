package com.cases.designPattern.builder;

import com.cases.designPattern.builder.impl.Director;

public class Client {
	public static void main(String[] args) {
		Director director = new Director();
		director.getABenzModel().run();

		director.getBBenzModel().run();

		director.getCBMWModel().run();

		director.getDBMWModel().run();
	}
}
