package com.cases.designPattern.command;

import com.cases.designPattern.command.impl.PageGroup;
import com.cases.designPattern.command.impl.RequirementGroup;

public abstract class Command {
	protected RequirementGroup rg = new RequirementGroup();
	protected PageGroup pg = new PageGroup();

	public abstract void execute();
}
