package com.cases.designPattern.prototype;

import java.util.ArrayList;

/**
 * 实现了Cloneable接口，是堆内存中二进制流的拷贝，比直接new一个对象快
 * 
 * clone只会拷贝基本类型，对于类中的对象和数组不进行拷贝
 * 
 * @author jinlong
 *
 */
public class Mail implements Cloneable {
	private String receiver;
	private String subject;
	private String appellation;
	private String context;
	private String tail;

	private ArrayList<String> list = new ArrayList<String>();

	public Mail(Advtemplate advtemplate) {
		this.subject = advtemplate.getAdvSubject();
		this.context = advtemplate.getAdvContext();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Mail clone() throws CloneNotSupportedException {
		Mail mail = null;
		mail = (Mail) super.clone();
		mail.list = (ArrayList<String>) this.list.clone();
		return mail;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAppellation() {
		return appellation;
	}

	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getTail() {
		return tail;
	}

	public void setTail(String tail) {
		this.tail = tail;
	}

}
