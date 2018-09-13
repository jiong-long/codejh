package com.jianghu.other.designPattern.decorator;

import com.jianghu.other.designPattern.decorator.impl.FouthGradeSchoolReport;
import com.jianghu.other.designPattern.decorator.impl.HighScoreDecorator;
import com.jianghu.other.designPattern.decorator.impl.SortDecorator;

public class Client {
	public static void main(String[] args) {
		SchoolReport report = new FouthGradeSchoolReport();
		report = new HighScoreDecorator(report);
		report = new SortDecorator(report);

		report.report();
		report.sign("xxx");
	}
}
