package com.cases.designPattern.mediator.impl;

import com.cases.designPattern.mediator.AbstractColleague;
import com.cases.designPattern.mediator.AbstractMediator;

/**
 * 库存管理
 * 
 * @author jinlong
 *
 */
public class Stock extends AbstractColleague {
	public Stock(AbstractMediator abstractMediator) {
		super(abstractMediator);
	}

	//初始数量
	private static int COMPUTER_NUMBER = 100;

	//库存增加
	public void increase(int number) {
		COMPUTER_NUMBER += number;
		System.out.println("库存数量为：" + COMPUTER_NUMBER);
	}

	//库存较少
	public void decrease(int number) {
		COMPUTER_NUMBER -= number;
		System.out.println("库存数量为：" + COMPUTER_NUMBER);
	}

	public int getStockNumber() {
		return COMPUTER_NUMBER;
	}

	//清理库存，让采购不要采购，销售尽快销售
	public void clearStock() {
		System.out.println("清理存货数量为：" + COMPUTER_NUMBER);
		super.mediator.execute("stock.clear");
	}
}
