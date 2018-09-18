package com.jianghu.service.basic.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.jianghu.dao.hibernate.GenericDAO;
import com.jianghu.domain.basic.User;
import com.jianghu.service.basic.UserServices;

/**
 * 用户services实现
 * 
 * @author jinlong
 * 
 */
public class UserServicesImpl implements UserServices {
	public GenericDAO<User> userDao;

	public GenericDAO<User> getUserDao() {
		return userDao;
	}

	public void setUserDao(GenericDAO<User> userDao) {
		this.userDao = userDao;
	}

	@Override
	public User login(String username) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("username", username));
		List<User> users = userDao.findByCriteria(criteria);
		if (users.size() == 0) {
			return null;
		}
		return users.get(0);
	}
}
