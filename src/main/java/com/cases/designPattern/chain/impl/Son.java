package com.cases.designPattern.chain.impl;

import com.cases.designPattern.chain.Handler;
import com.cases.designPattern.chain.IWomen;

public class Son extends Handler {

	public Son() {
		super(Handler.SON_LEVEL_REQUEST);
	}

	@Override
	protected void response(IWomen women) {
		System.out.println("母亲的请示是：" + women.getRequest());
		System.out.println("儿子的答复是：同意");
	}

}
