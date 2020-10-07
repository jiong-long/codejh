package com.cases.designPattern.decorator;

public abstract class Decorator extends SchoolReport {
	private SchoolReport report;

	public Decorator(SchoolReport report) {
		super();
		this.report = report;
	}

	@Override
	public void report() {
		report.report();
	}

	@Override
	public void sign(String name) {
		report.sign(name);
	}
}
