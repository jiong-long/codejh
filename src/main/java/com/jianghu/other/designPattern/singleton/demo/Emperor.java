package com.jianghu.other.designPattern.singleton.demo;

/**
 * 皇帝，只能有一个
 * 
 * @author jinlong
 *
 */
public class Emperor {
	private static final Emperor emperor = new Emperor();

	public Emperor() {
	}

	public static Emperor getInstance() {
		return emperor;
	}

	public void say() {
		System.out.println("我就是皇帝某某某……");
	}
}
