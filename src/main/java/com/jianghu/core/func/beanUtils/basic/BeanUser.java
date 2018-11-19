package com.jianghu.core.func.beanUtils.basic;

import java.util.Date;

public class BeanUser {
	private String username;
	private String password;
	private Date birthday;

	@Override
	public String toString() {
		return "BeanUser [username=" + username + ", password=" + password + ", birthday=" + birthday + "]";
	}

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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
