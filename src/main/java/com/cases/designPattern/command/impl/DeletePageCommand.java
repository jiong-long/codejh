package com.cases.designPattern.command.impl;

import com.cases.designPattern.command.Command;

public class DeletePageCommand extends Command {

	@Override
	public void execute() {
		super.pg.find();
		super.pg.delete();
		super.pg.plan();
	}

}
