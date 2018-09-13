package com.jianghu.other.designPattern.state;

import com.jianghu.other.designPattern.state.impl.ClosingState;
import com.jianghu.other.designPattern.state.impl.Context;

public class Client {
	public static void main(String[] args) {
		Context context = new Context();
		context.setLiftState(new ClosingState());
		context.open();
		context.close();
		context.run();
		context.stop();
	}
}
