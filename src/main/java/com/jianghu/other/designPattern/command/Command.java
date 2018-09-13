package com.jianghu.other.designPattern.command;

import com.jianghu.other.designPattern.command.impl.PageGroup;
import com.jianghu.other.designPattern.command.impl.RequirementGroup;

public abstract class Command {
	protected RequirementGroup rg = new RequirementGroup();
	protected PageGroup pg = new PageGroup();

	public abstract void execute();
}
