package com.cases.designPattern.mediator.impl;

import java.util.Random;

import com.cases.designPattern.mediator.AbstractColleague;
import com.cases.designPattern.mediator.AbstractMediator;

/**
 * 销售管理
 * 
 * @author jinlong
 *
 */
public class Sale extends AbstractColleague {
	public Sale(AbstractMediator abstractMediator) {
		super(abstractMediator);
	}

	//销售电脑
	public void sellIBMComputer(int number) {
		super.mediator.execute("sale.sell", number);
		System.out.println("销售IBM电脑" + number + "台");
	}

	//反馈销售情况
	public int getSaleStatus() {
		Random random = new Random();
		int saleStatus = random.nextInt(100);
		System.out.println("IBM电脑的销售情况为：" + saleStatus);
		return saleStatus;
	}

	//折价处理
	public void offSale() {
		super.mediator.execute("sale.offsell");
	}
}
