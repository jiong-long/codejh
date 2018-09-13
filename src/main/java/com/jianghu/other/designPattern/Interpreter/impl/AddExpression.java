package com.jianghu.other.designPattern.Interpreter.impl;

import java.util.HashMap;

import com.jianghu.other.designPattern.Interpreter.Expression;
import com.jianghu.other.designPattern.Interpreter.SymbolExpression;

public class AddExpression extends SymbolExpression {

	public AddExpression(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public int interpreter(HashMap<String, Integer> map) {
		return super.left.interpreter(map) + super.right.interpreter(map);
	}

}
