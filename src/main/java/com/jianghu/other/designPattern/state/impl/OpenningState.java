package com.jianghu.other.designPattern.state.impl;

import com.jianghu.other.designPattern.state.LiftState;

public class OpenningState extends LiftState {
	public void close() {
		super.context.setLiftState(Context.closeingState);
		super.context.getLiftState().close();
	}

	public void open() {
		System.out.println("电梯门开启");
	}

	public void run() {

	}

	public void stop() {

	}
}
