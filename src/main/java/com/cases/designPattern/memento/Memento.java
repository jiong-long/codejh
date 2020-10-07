package com.cases.designPattern.memento;

public class Memento {
	private String state = "";

	public Memento(String state) {
		super();
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
