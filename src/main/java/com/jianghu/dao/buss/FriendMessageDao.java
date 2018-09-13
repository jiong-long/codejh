package com.jianghu.dao.buss;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.jianghu.dao.impl.GenericDAOImpl;
import com.jianghu.domain.layim.FriendMessage;

@Repository("friendMessageDao")
public class FriendMessageDao extends GenericDAOImpl<FriendMessage> {
	public FriendMessageDao() {
		super();
	}

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

}
