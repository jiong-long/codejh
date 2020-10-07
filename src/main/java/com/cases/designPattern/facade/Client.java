package com.cases.designPattern.facade;

import com.cases.designPattern.facade.impl.ModenPostOffice;

public class Client {
	public static void main(String[] args) {
		ModenPostOffice postOffice = new ModenPostOffice();
		postOffice.sendLetter("信的内容是三生三世十里桃花", "地址是谁谁谁");
	}
}
