package com.jianghu.dao.hibernate.buss;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.jianghu.dao.hibernate.impl.GenericDAOImpl;
import com.jianghu.domain.basic.Task;

@Repository("taskDao")
public class TaskDaoImpl extends GenericDAOImpl<Task> {
	public TaskDaoImpl() {
		super("com.jianghu.domain.basic.Task");
	}

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

}
