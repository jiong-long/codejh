package com.cases.designPattern.command;

import com.cases.designPattern.command.impl.AddRequirementCommand;
import com.cases.designPattern.command.impl.Invoker;

public class Client {
	public static void main(String[] args) {
		Invoker invoker = new Invoker();

		Command command = new AddRequirementCommand();
		invoker.setCommand(command);

		invoker.action();
	}
}
