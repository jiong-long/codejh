package com.jianghu.service.layim.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianghu.dao.hibernate.GenericDAO;
import com.jianghu.domain.layim.Group;
import com.jianghu.service.layim.GroupServices;

@Service("groupServices")
@Transactional
public class GroupServicesImpl implements GroupServices {

	@Resource(name = "groupDao")
	private GenericDAO<Group> groupDao;

	@Override
	public Group getGroupById(String id) {
		return groupDao.findById(Group.class, Integer.valueOf(id));
	}

}
