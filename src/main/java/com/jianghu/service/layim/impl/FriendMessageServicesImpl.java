package com.jianghu.service.layim.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianghu.dao.GenericDAO;
import com.jianghu.domain.layim.FriendMessage;
import com.jianghu.service.layim.FriendMessageServices;

@Service("friendMessageServices")
@Transactional
public class FriendMessageServicesImpl implements FriendMessageServices {

	@Resource(name = "friendMessageDao")
	private GenericDAO<FriendMessage> friendMessageDao;

	@Override
	public void save(FriendMessage friendMessage) {
		friendMessageDao.save(friendMessage);
	}
}
