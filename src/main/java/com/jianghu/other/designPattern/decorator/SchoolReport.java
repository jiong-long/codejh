package com.jianghu.other.designPattern.decorator;

public abstract class SchoolReport {
	public void report() {
		System.out.println("尊敬的XXX家长");
		System.out.println("语文 62 数学 65 自然 63");
		System.out.println("家长签名：           ");
	}

	public abstract void sign(String name);
}
