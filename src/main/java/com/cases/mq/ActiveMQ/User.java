package com.cases.mq.ActiveMQ;

import java.io.Serializable;

/**
 * 对象必须实现Serializable
 * 
 * @creatTime 2018年10月25日 上午12:00:51
 * @author jinlong
 */
public class User implements Serializable {
	private static final long serialVersionUID = 8554334560393854210L;

	private int id;
	private String name;

	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
