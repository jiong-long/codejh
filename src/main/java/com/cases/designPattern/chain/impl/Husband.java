package com.cases.designPattern.chain.impl;

import com.cases.designPattern.chain.Handler;
import com.cases.designPattern.chain.IWomen;

public class Husband extends Handler {

	public Husband() {
		super(Handler.HUSBAND_LEVEL_REQUEST);
	}

	@Override
	protected void response(IWomen women) {
		System.out.println("妻子的请示是：" + women.getRequest());
		System.out.println("丈夫的答复是：同意");
	}

}
