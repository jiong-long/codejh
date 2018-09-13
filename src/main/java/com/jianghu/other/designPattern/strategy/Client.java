package com.jianghu.other.designPattern.strategy;

import com.jianghu.other.designPattern.strategy.impl.BackDoor;
import com.jianghu.other.designPattern.strategy.impl.BlockEnemy;
import com.jianghu.other.designPattern.strategy.impl.Context;
import com.jianghu.other.designPattern.strategy.impl.GiveGreenLight;

public class Client {
	public static void main(String[] args) {
		Context context = new Context(new BackDoor());
		context.operate();

		context = new Context(new GiveGreenLight());
		context.operate();

		context = new Context(new BlockEnemy());
		context.operate();
	}
}
