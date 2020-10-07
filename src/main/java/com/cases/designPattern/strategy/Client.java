package com.cases.designPattern.strategy;

import com.cases.designPattern.strategy.impl.BackDoor;
import com.cases.designPattern.strategy.impl.BlockEnemy;
import com.cases.designPattern.strategy.impl.Context;
import com.cases.designPattern.strategy.impl.GiveGreenLight;

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
