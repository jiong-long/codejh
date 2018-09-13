package com.jianghu.other.designPattern.facade.impl;

import com.jianghu.other.designPattern.facade.ILetterProcess;

public class Police {
	public void checkLetter(ILetterProcess letterProcess) {
		System.out.println(letterProcess + "信件已经检查过了");
	}
}
