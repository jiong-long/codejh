package com.jianghu.other.designPattern.adapter.impl;

import com.jianghu.other.designPattern.adapter.IUserInfo;

public class UserInfo implements IUserInfo {

	@Override
	public String getUserName() {
		System.out.println("姓名叫做");
		return null;
	}

	@Override
	public String getHomeAddress() {
		System.out.println("这是员工的家庭住址");
		return null;
	}

	@Override
	public String getMobileNumber() {
		System.out.println("员工的手机号是1234");
		return null;
	}

	@Override
	public String getOfficeTelNumber() {
		System.out.println("员工的办公电话是4321");
		return null;
	}

	@Override
	public String getJobPosition() {
		System.out.println("这个人的职务是BOSS");
		return null;
	}

	@Override
	public String getHomeTelNumber() {
		System.out.println("家庭电话是789");
		return null;
	}

}
