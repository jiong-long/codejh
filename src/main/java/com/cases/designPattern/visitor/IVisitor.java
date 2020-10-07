package com.cases.designPattern.visitor;

import com.cases.designPattern.visitor.impl.CommonEmployee;
import com.cases.designPattern.visitor.impl.Manager;

public interface IVisitor {
	public void visit(CommonEmployee commonEmployee);

	public void visit(Manager manager);
}
