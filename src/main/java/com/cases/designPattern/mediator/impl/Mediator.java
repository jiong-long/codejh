package com.cases.designPattern.mediator.impl;

import com.cases.designPattern.mediator.AbstractMediator;

public class Mediator extends AbstractMediator {

	@Override
	public void execute(String str, Object... objects) {
		if (str.equals("purchase.buy")) {//采购电脑
			this.buyComputer((Integer) objects[0]);
		} else if (str.equals("sale.sell")) {//销售电脑
			this.sellComputer((Integer) objects[0]);
		} else if (str.equals("sale.offsell")) {//折价销售
			this.offSale();
		} else if (str.equals("stock.clear")) {//清仓处理
			this.clearStock();
		}
	}

	//清理库存，让采购不要采购，销售尽快销售
	private void clearStock() {
		super.sale.offSale();
		super.purchase.refuseBuyIBM();
	}

	//折价处理
	private void offSale() {
		System.out.println("折价销售IBM电脑" + super.stock.getStockNumber() + "台。");
	}

	//销售电脑
	private void sellComputer(int number) {
		if (super.stock.getStockNumber() < number) {
			super.purchase.buyIBMcomputer(number);
		}
		System.out.println("销售IBM电脑" + number + "台");
		super.stock.decrease(number);
	}

	//采购电脑
	private void buyComputer(int number) {
		int saleStatus = super.sale.getSaleStatus();
		if (saleStatus > 80) {
			System.out.println("采购IBM电脑：" + number + "台");
			stock.increase(number);
		} else {
			int buyNumber = number / 2;
			System.out.println("采购IBM电脑：" + buyNumber + "台");
			stock.increase(buyNumber);
		}
	}

}
