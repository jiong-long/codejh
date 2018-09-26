package com.jianghu.service.mybatis;

import java.util.List;

import com.jianghu.dao.mybatis.plus.UserPlus;

public interface UserService {
	public List<UserPlus> selectAllUser() throws Exception;
}
