package com.jianghu.other.designPattern.command.impl;

import com.jianghu.other.designPattern.command.Command;

public class DeletePageCommand extends Command {

	@Override
	public void execute() {
		super.pg.find();
		super.pg.delete();
		super.pg.plan();
	}

}
