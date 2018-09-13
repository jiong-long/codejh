package com.jianghu.domain.pcAndMail;

import java.math.BigDecimal;

/**
 * 邮箱
 * 
 * @creatTime 2016年10月23日 下午9:43:00
 * @author jinlong
 * 
 */
public class Mails {
	private BigDecimal id;// 主键
	private String mails;// 邮箱

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getMails() {
		return mails;
	}

	public void setMails(String mails) {
		this.mails = mails;
	}

}
