package com.jianghu.other.designPattern.adapter;

import com.jianghu.other.designPattern.adapter.impl.OuterUserInfo;
import com.jianghu.other.designPattern.adapter.impl.UserInfo;

public class Client {
	public static void main(String[] args) {
		IUserInfo userInfo = new UserInfo();
		userInfo.getMobileNumber();

		IUserInfo outerUserInfo = new OuterUserInfo();
		outerUserInfo.getMobileNumber();
	}
}
