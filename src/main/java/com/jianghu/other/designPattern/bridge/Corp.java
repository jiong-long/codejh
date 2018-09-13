package com.jianghu.other.designPattern.bridge;

public abstract class Corp {
	private Product product;

	public Corp(Product product) {
		super();
		this.product = product;
	}

	public void makeMoney() {
		this.product.beProducted();
		this.product.beSelled();
	}
}
