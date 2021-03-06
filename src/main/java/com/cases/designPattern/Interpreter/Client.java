package com.cases.designPattern.Interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.cases.designPattern.Interpreter.impl.Calculator;

public class Client {
	public static void main(String[] args) throws IOException {
		String expStr = getExpStr();
		HashMap<String, Integer> map = getValue(expStr);
		Calculator calculator = new Calculator(expStr);
		System.out.println("运算结果为：" + expStr + "=" + calculator.run(map));
	}

	public static String getExpStr() throws IOException {
		System.out.println("请输入表达式：");
		return (new BufferedReader(new InputStreamReader(System.in))).readLine();
	}

	public static HashMap<String, Integer> getValue(String exprStr) throws IOException {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (char ch : exprStr.toCharArray()) {
			if (ch != '+' && ch != '-') {
				if (!map.containsKey(String.valueOf(ch))) {
					String in = (new BufferedReader(new InputStreamReader(System.in))).readLine();
					if (in != null) {
						map.put(String.valueOf(ch), Integer.parseInt(in));
					}
				}
			}
		}
		return map;
	}
}
