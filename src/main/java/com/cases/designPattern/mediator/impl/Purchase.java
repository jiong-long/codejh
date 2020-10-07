package com.cases.designPattern.mediator.impl;

import com.cases.designPattern.mediator.AbstractColleague;
import com.cases.designPattern.mediator.AbstractMediator;

/**
 * 采购管理
 * 
 * @author jinlong
 *
 */
public class Purchase extends AbstractColleague {
	public Purchase(AbstractMediator abstractMediator) {
		super(abstractMediator);
	}

	public void buyIBMcomputer(int number) {
		super.mediator.execute("purchase.buy", number);
	}

	public void refuseBuyIBM() {
		System.out.println("不再采购IBM电脑");
	}
}
