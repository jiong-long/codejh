package com.jianghu.dao.hibernate.buss;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.jianghu.dao.hibernate.impl.GenericDAOImpl;
import com.jianghu.domain.layim.Mine;

@Repository("groupDao")
public class GroupDao extends GenericDAOImpl<Mine> {
	public GroupDao() {
		super();
	}

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

}
