package com.cases.designPattern.adapter.impl;

import java.util.HashMap;
import java.util.Map;

import com.cases.designPattern.adapter.IOuterUser;

public class OuterUser implements IOuterUser {

	@Override
	public Map<String, String> getUserBaseInfo() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", "这个员工的姓名是111");
		map.put("mobileNumber", "这个员工的电话是1234");
		return map;
	}

	@Override
	public Map<String, String> getUserOfficeInfo() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("jobPosition", "这个员工的职位");
		map.put("officeTelNumber", "这个员工的办公电话");
		return map;
	}

	@Override
	public Map<String, String> getUserHomeInfo() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("homeTelNumber", "这个员工的家庭电话");
		map.put("homeAddress", "这个员工的家庭住址");
		return map;
	}

}
