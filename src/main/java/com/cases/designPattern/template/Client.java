package com.cases.designPattern.template;

/**
 * 模板设计模式是把设计模式拉下神坛，让我们知道设计模式我们平时都在使用
 * 
 * @author jinlong
 *
 */
public class Client {
	public static void main(String[] args) {
		HummerModel h1 = new HummerH1Model();
		h1.run();

		HummerModel h2 = new HummerH2Model();
		h2.run();
	}
}
