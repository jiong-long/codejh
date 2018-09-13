package com.jianghu.other.designPattern.state.impl;

import com.jianghu.other.designPattern.state.LiftState;

public class ClosingState extends LiftState {
	public void close() {
		System.out.println("电梯门关闭");
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
		super.context.setLiftState(Context.stoppingState);
		super.context.getLiftState().stop();
	}
}
