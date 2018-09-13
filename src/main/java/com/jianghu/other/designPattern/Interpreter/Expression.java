package com.jianghu.other.designPattern.Interpreter;

import java.util.HashMap;

public abstract class Expression {
	public abstract int interpreter(HashMap<String, Integer> map);
}
