package com.jianghu.other.designPattern.state.impl;

import com.jianghu.other.designPattern.state.LiftState;

public class StoppingState extends LiftState {
	public void close() {
	}

	public void open() {
		super.context.setLiftState(Context.openingState);
		super.context.getLiftState().open();
	}

	public void run() {
		super.context.setLiftState(Context.runningState);
		super.context.getLiftState().run();
	}

	public void stop() {
		System.out.println("电梯停止了");
	}
}
