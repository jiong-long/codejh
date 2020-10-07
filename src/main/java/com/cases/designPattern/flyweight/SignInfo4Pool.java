package com.cases.designPattern.flyweight;

public class SignInfo4Pool extends SignInfo {
	private String key;

	public SignInfo4Pool(String key) {
		super();
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
