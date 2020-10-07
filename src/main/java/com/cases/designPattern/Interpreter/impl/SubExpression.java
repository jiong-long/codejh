package com.cases.designPattern.Interpreter.impl;

import java.util.HashMap;

import com.cases.designPattern.Interpreter.Expression;
import com.cases.designPattern.Interpreter.SymbolExpression;

public class SubExpression extends SymbolExpression {

	public SubExpression(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public int interpreter(HashMap<String, Integer> map) {
		return super.left.interpreter(map) - super.right.interpreter(map);
	}

}
