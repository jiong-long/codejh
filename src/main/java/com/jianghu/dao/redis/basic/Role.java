package com.jianghu.dao.redis.basic;

import java.io.Serializable;

public class Role implements Serializable {
	private static final long serialVersionUID = 7812917945153643163L;
	private int id;
	private String name;

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

	public Role(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
