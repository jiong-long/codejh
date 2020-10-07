package com.cases.designPattern.decorator;

import com.cases.designPattern.decorator.impl.FouthGradeSchoolReport;
import com.cases.designPattern.decorator.impl.HighScoreDecorator;
import com.cases.designPattern.decorator.impl.SortDecorator;

public class Client {
	public static void main(String[] args) {
		SchoolReport report = new FouthGradeSchoolReport();
		report = new HighScoreDecorator(report);
		report = new SortDecorator(report);

		report.report();
		report.sign("xxx");
	}
}
