package com.jianghu.service.mybatis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jianghu.dao.mybatis.mapper.UserMapper;
import com.jianghu.dao.mybatis.plus.UserPlus;
import com.jianghu.service.mybatis.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<UserPlus> selectAllUser() throws Exception {
		return userMapper.selectAllUser();
	}

}