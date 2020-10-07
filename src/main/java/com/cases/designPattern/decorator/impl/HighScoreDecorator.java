package com.cases.designPattern.decorator.impl;

import com.cases.designPattern.decorator.Decorator;
import com.cases.designPattern.decorator.SchoolReport;

public class HighScoreDecorator extends Decorator {

	public HighScoreDecorator(SchoolReport report) {
		super(report);
	}

	private void reportHighScore() {
		System.out.println("这次考试语文最高分是75，数据是78，自然是80");
	}

	@Override
	public void report() {
		reportHighScore();
		super.report();
	}
}
