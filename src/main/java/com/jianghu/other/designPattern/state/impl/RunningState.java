package com.jianghu.other.designPattern.state.impl;

import com.jianghu.other.designPattern.state.LiftState;

public class RunningState extends LiftState {
	@Override
	public void close() {
	}

	@Override
	public void open() {
	}

	@Override
	public void run() {
		System.out.println("电梯上下运行中");
	}

	@Override
	public void stop() {
		super.context.setLiftState(Context.stoppingState);
		super.context.getLiftState().stop();
	}
}
