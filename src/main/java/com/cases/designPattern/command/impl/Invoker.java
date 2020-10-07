package com.cases.designPattern.command.impl;

import com.cases.designPattern.command.Command;

public class Invoker {
	private Command command;

	public void action() {
		this.command.execute();
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}
}
