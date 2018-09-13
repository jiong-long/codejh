package com.jianghu.other.designPattern.mediator;

public abstract class AbstractColleague {
	protected AbstractMediator mediator;

	public AbstractColleague(AbstractMediator abstractMediator) {
		mediator = abstractMediator;
	}
}
