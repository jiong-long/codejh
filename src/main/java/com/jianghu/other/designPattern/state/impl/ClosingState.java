package com.jianghu.other.designPattern.state.impl;

import com.jianghu.other.designPattern.state.LiftState;

public class ClosingState extends LiftState {
	@Override
	public void close() {
		System.out.println("电梯门关闭");
	}

	@Override
	public void open() {
		super.context.setLiftState(Context.openingState);
		super.context.getLiftState().open();
	}

	@Override
	public void run() {
		super.context.setLiftState(Context.runningState);
		super.context.getLiftState().run();
	}

	@Override
	public void stop() {
		super.context.setLiftState(Context.stoppingState);
		super.context.getLiftState().stop();
	}
}
