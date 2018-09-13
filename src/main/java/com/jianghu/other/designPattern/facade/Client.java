package com.jianghu.other.designPattern.facade;

import com.jianghu.other.designPattern.facade.impl.ModenPostOffice;

public class Client {
	public static void main(String[] args) {
		ModenPostOffice postOffice = new ModenPostOffice();
		postOffice.sendLetter("信的内容是三生三世十里桃花", "地址是谁谁谁");
	}
}
