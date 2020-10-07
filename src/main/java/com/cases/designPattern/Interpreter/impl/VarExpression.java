package com.cases.designPattern.Interpreter.impl;

import java.util.HashMap;

import com.cases.designPattern.Interpreter.Expression;

public class VarExpression extends Expression {
	private String key;

	public VarExpression(String key) {
		super();
		this.key = key;
	}

	@Override
	public int interpreter(HashMap<String, Integer> map) {
		return map.get(key);
	}

}
