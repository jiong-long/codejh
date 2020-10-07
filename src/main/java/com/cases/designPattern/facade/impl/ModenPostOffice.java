package com.cases.designPattern.facade.impl;

import com.cases.designPattern.facade.ILetterProcess;

public class ModenPostOffice {
	private ILetterProcess letterProcess = new LetterProcessImpl();

	private Police police = new Police();

	public void sendLetter(String context, String address) {
		letterProcess.writeContext(context);
		letterProcess.fillEnvelope(address);
		police.checkLetter(letterProcess);
		letterProcess.letterInotoEnvelope();
		letterProcess.sendLetter();
	}

}
