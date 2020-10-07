package com.cases.designPattern.mediator;

import com.cases.designPattern.mediator.impl.Purchase;
import com.cases.designPattern.mediator.impl.Sale;
import com.cases.designPattern.mediator.impl.Stock;

public abstract class AbstractMediator {
	protected Purchase purchase;
	protected Sale sale;
	protected Stock stock;

	public AbstractMediator() {
		purchase = new Purchase(this);
		sale = new Sale(this);
		stock = new Stock(this);
	}

	//中介者最重要的方法叫做事件方法，处理多个对象之间的关系
	public abstract void execute(String str, Object... objects);
}
