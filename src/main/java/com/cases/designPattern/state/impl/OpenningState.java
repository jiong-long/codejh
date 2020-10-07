package com.cases.designPattern.state.impl;

import com.cases.designPattern.state.LiftState;

public class OpenningState extends LiftState {
	@Override
	public void close() {
		super.context.setLiftState(Context.closeingState);
		super.context.getLiftState().close();
	}

	@Override
	public void open() {
		System.out.println("电梯门开启");
	}

	@Override
	public void run() {

	}

	@Override
	public void stop() {

	}
}
