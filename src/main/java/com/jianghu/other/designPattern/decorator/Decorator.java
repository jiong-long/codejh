package com.jianghu.other.designPattern.decorator;

public abstract class Decorator extends SchoolReport {
	private SchoolReport report;

	public Decorator(SchoolReport report) {
		super();
		this.report = report;
	}

	public void report() {
		report.report();
	}

	public void sign(String name) {
		report.sign(name);
	}
}
