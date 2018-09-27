package com.jianghu.other.designPattern.prototype;

import java.util.Random;

public class Client {
	private static int MAX_COUNT = 6;

	public static void main(String[] args) throws CloneNotSupportedException {
		int i = 0;
		Mail mail = new Mail(new Advtemplate());
		mail.setTail("XX银行版权所有");
		while (i < MAX_COUNT) {
			//不通过new来产生对象，而是通过对象复制来实现的模式就叫做原型模式(由于产生了新的对象所以是线程安全的)
			Mail cloneMail = mail.clone();
			cloneMail.setAppellation(getRandString(5) + "先生（女士）");
			cloneMail.setReceiver(getRandString(5) + "@" + getRandString(8) + ".com");
			sendMail(cloneMail);
			i++;
		}
	}

	public static void sendMail(Mail mail) {
		System.out.println("标题：" + mail.getSubject() + "\t收件人：" + mail.getReceiver() + "\t发送成功");
	}

	public static String getRandString(int maxLength) {
		String source = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < maxLength; i++) {
			buffer.append(source.charAt(random.nextInt(source.length())));
		}
		return buffer.toString();
	}
}
