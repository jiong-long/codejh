package com.jianghu.other.designPattern.visitor;

import com.jianghu.other.designPattern.visitor.impl.CommonEmployee;
import com.jianghu.other.designPattern.visitor.impl.Manager;

public interface IVisitor {
	public void visit(CommonEmployee commonEmployee);

	public void visit(Manager manager);
}
