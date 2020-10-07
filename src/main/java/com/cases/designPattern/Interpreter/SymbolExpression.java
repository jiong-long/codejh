package com.cases.designPattern.Interpreter;

public abstract class SymbolExpression extends Expression {
	protected Expression left;
	protected Expression right;

	public SymbolExpression(Expression left, Expression right) {
		super();
		this.left = left;
		this.right = right;
	}

}
