package com.cases.office.exportWord;

/**
 * word中表格已对象形式返回
 * 
 * @creatTime 2018年11月18日 上午11:07:12
 * @author jinlong
 */
public class WordUser {
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public WordUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

}
