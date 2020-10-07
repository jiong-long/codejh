package com.cases.designPattern.adapter;

import com.cases.designPattern.adapter.impl.OuterUserInfo;
import com.cases.designPattern.adapter.impl.UserInfo;

public class Client {
	public static void main(String[] args) {
		IUserInfo userInfo = new UserInfo();
		userInfo.getMobileNumber();

		IUserInfo outerUserInfo = new OuterUserInfo();
		outerUserInfo.getMobileNumber();
	}
}
