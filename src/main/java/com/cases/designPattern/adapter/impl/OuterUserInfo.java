package com.cases.designPattern.adapter.impl;

import java.util.Map;

import com.cases.designPattern.adapter.IUserInfo;

/**
 * 适配器类
 * 
 * 继承外部的类，实现内部的类，这样内部的代码改动较小
 * 
 * @author jinlong
 *
 */
public class OuterUserInfo extends OuterUser implements IUserInfo {
	private Map<String, String> baseInfo = super.getUserBaseInfo();
	private Map<String, String> homeInfo = super.getUserHomeInfo();
	private Map<String, String> officeInfo = super.getUserOfficeInfo();

	@Override
	public String getUserName() {
		return baseInfo.get("userName");
	}

	@Override
	public String getHomeAddress() {
		return homeInfo.get("homeAddress");
	}

	@Override
	public String getMobileNumber() {
		return baseInfo.get("mobileNumber");
	}

	@Override
	public String getOfficeTelNumber() {
		return officeInfo.get("officeTelNumber");
	}

	@Override
	public String getJobPosition() {
		return officeInfo.get("jobPosition");
	}

	@Override
	public String getHomeTelNumber() {
		return homeInfo.get("homeTelNumber");
	}

}
