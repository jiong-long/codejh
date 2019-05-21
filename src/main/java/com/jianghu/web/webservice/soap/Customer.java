package com.jianghu.web.webservice.soap;

/**
 * 用户对象
 * @author wangjinlong
 *
 */
public class Customer {
	//编号
	private String id;
	//名称
	private String name;
	//关联人员
	private CustomerUser[][] userArr;
	
	public CustomerUser[][] getUserArr() {
		return userArr;
	}
	public void setUserArr(CustomerUser[][] userArr) {
		this.userArr = userArr;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
