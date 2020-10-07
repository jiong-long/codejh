package com.cases.designPattern.command.impl;

import com.cases.designPattern.command.Command;

public class AddRequirementCommand extends Command {

	@Override
	public void execute() {
		super.rg.find();
		super.rg.add();
		super.rg.plan();
	}

}
