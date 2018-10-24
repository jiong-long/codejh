package com.jianghu.dao.redis;

public interface IRedisService<K, V> {
	public void set(K key, V value, long expiredTime);

	public void set(K key, V value);

	public V get(K key);

	public Object getHash(K key, String name);

	public void del(K key);
}