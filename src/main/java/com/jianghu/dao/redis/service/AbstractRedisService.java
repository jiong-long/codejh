package com.jianghu.dao.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public abstract class AbstractRedisService<K, V> implements IRedisService<K, V> {

	@Autowired
	private RedisTemplate<K, V> redisTemplate;
	RedisSerializer<String> stringSerializer = new StringRedisSerializer();

	public RedisTemplate<K, V> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void set(final K key, final V value) {
		// 解决key值保存到redis服务器出现乱码的情况
		// 限制：只能接受String类型的key值
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.opsForValue().set(key, value);
		//BoundValueOperations<K, V> valueOper = redisTemplate.boundValueOps(key);
		//valueOper.set(value);
	}

	@Override
	public V get(final K key) {
		return redisTemplate.opsForValue().get(key);
		//BoundValueOperations<K, V> valueOper = redisTemplate.boundValueOps(key);
		//return valueOper.get();
	}

	@Override
	public void del(K key) {
		if (redisTemplate.hasKey(key)) {
			redisTemplate.delete(key);
		}
	}

}
