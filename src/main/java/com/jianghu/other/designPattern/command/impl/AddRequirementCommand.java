package com.jianghu.other.designPattern.command.impl;

import com.jianghu.other.designPattern.command.Command;

public class AddRequirementCommand extends Command {

	@Override
	public void execute() {
		super.rg.find();
		super.rg.add();
		super.rg.plan();
	}

}
