package com.jianghu.other.designPattern.adapter;

import java.util.Map;

public interface IOuterUser {
	public Map<String, String> getUserBaseInfo();

	public Map<String, String> getUserOfficeInfo();

	public Map<String, String> getUserHomeInfo();
}
