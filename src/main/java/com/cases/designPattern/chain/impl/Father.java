package com.cases.designPattern.chain.impl;

import com.cases.designPattern.chain.Handler;
import com.cases.designPattern.chain.IWomen;

public class Father extends Handler {

	public Father() {
		super(Handler.FATHER_LEVEL_REQUEST);
	}

	@Override
	protected void response(IWomen women) {
		System.out.println("女儿的请示是：" + women.getRequest());
		System.out.println("父亲的答复是：同意");
	}

}
