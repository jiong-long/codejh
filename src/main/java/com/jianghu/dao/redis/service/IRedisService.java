package com.jianghu.dao.redis.service;

/**
 * redis操作相关接口
 * 
 * @creatTime 2018年10月25日 上午12:40:44
 * @author jinlong
 * @param <K>
 * @param <V>
 */
public interface IRedisService<K, V> {
	//保存
	public void set(K key, V value);

	//查询
	public V get(K key);

	//删除
	public void del(K key);
}