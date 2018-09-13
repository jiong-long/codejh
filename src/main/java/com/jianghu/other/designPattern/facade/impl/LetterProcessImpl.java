package com.jianghu.other.designPattern.facade.impl;

import com.jianghu.other.designPattern.facade.ILetterProcess;

public class LetterProcessImpl implements ILetterProcess {

	@Override
	public void writeContext(String context) {
		System.out.println("填写信的内容：" + context);
	}

	@Override
	public void fillEnvelope(String address) {
		System.out.println("填写信的地址：" + address);
	}

	@Override
	public void letterInotoEnvelope() {
		System.out.println("把信放到信封中");
	}

	@Override
	public void sendLetter() {
		System.out.println("邮递信件");
	}

}
