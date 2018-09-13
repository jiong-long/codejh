package com.jianghu.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 通用DAO接口
 * 
 * 
 */
public interface GenericDAO<T> {
	// 增加
	public Serializable save(T obj);

	// 删除
	public void delete(T obj);

	// 修改
	public void update(T obj);

	// 查询 如果id自增，定义为Integer或者 Long
	public T findById(Class<T> domainClass, Serializable id);

	// 查询 如果id自增，定义为Integer或者 Long
	public T findById(Class<T> domainClass, String id);

	// 查询所有
	public List<T> findAll();

	// 各种各样条件查询
	public List<T> findByCriteria(DetachedCriteria criteria);

	// 分页查询
	public List<T> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults);

	// 进行条件查询，使用hql
	public List<T> findByHqlQuery(String namedQuery, Object... args);

	// 查询目标记录总数
	public int findByTotalCount();
}
