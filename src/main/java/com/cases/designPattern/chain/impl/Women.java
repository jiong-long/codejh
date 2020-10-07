package com.cases.designPattern.chain.impl;

import com.cases.designPattern.chain.IWomen;

public class Women implements IWomen {
	private int type = 0;
	private String request = "";

	public Women(int type, String request) {
		super();
		this.type = type;
		this.request = request;
	}

	@Override
	public int getType() {
		return this.type;
	}

	@Override
	public String getRequest() {
		return this.request;
	}

}
