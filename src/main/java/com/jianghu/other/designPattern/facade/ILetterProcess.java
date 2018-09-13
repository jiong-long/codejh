package com.jianghu.other.designPattern.facade;

public interface ILetterProcess {
	public void writeContext(String context);

	public void fillEnvelope(String address);

	public void letterInotoEnvelope();

	public void sendLetter();
}
