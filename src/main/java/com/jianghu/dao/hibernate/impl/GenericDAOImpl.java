package com.jianghu.dao.hibernate.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jianghu.dao.hibernate.GenericDAO;

// 实现GenericDAO 接口
public class GenericDAOImpl<T> extends HibernateDaoSupport implements GenericDAO<T> {

	private Class<T> domainClass;

	public GenericDAOImpl() {

	}

	@SuppressWarnings("unchecked")
	public GenericDAOImpl(String domainClassName) {
		try {
			this.domainClass = (Class<T>) Class.forName(domainClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Serializable save(T obj) {
		return this.getHibernateTemplate().save(obj);
	}

	@Override
	public void delete(T obj) {
		this.getHibernateTemplate().delete(obj);
	}

	@Override
	public void update(T obj) {
		this.getHibernateTemplate().update(obj);
	}

	@Override
	public T findById(Class<T> domainClass, Serializable id) {
		return (T) this.getHibernateTemplate().get(domainClass, id);
	}

	@Override
	public T findById(Class<T> domainClass, String id) {
		return (T) this.getHibernateTemplate().get(domainClass, Long.parseLong(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from " + domainClass.getName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByCriteria(DetachedCriteria criteria) {
		return (List<T>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults) {
		return (List<T>) this.getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByHqlQuery(String hql, Object... args) {
		return (List<T>) this.getHibernateTemplate().find(hql, args);
	}

	// 查询总记录条数
	@Override
	public int findByTotalCount() {
		@SuppressWarnings("rawtypes")
		List list = this.getHibernateTemplate().find("select count(*) from " + domainClass.getName());
		long temp = (Long) list.get(0);
		return (int) temp;
	}

}
