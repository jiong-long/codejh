package com.jianghu.other.designPattern.command;

import com.jianghu.other.designPattern.command.impl.AddRequirementCommand;
import com.jianghu.other.designPattern.command.impl.Invoker;

public class Client {
	public static void main(String[] args) {
		Invoker invoker = new Invoker();

		Command command = new AddRequirementCommand();
		invoker.setCommand(command);

		invoker.action();
	}
}
