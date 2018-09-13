package com.jianghu.other.designPattern.state.impl;

import com.jianghu.other.designPattern.state.LiftState;

public class RunningState extends LiftState {
	public void close() {
	}

	public void open() {
	}

	public void run() {
		System.out.println("电梯上下运行中");
	}

	public void stop() {
		super.context.setLiftState(Context.stoppingState);
		super.context.getLiftState().stop();
	}
}
