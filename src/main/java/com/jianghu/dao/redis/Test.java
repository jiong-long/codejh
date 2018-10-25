package com.jianghu.dao.redis;

import java.util.List;

import com.jianghu.dao.redis.Tools.RedisTool;
import com.jianghu.dao.redis.Tools.SerializeUtil;
import com.jianghu.dao.redis.basic.Role;
import com.jianghu.dao.redis.basic.User;
import com.jianghu.dao.redis.service.RedisService;

import redis.clients.jedis.Jedis;

public class Test {
	public static void main(String[] args) {
		remoteTest();
		//localhostTest();
	}

	/**
	 * 配置文件版Redis操作
	 */
	public static void remoteTest() {
		RedisService rs = RedisTool.getRedisService();
		//存储对象
		User user = new User(100, "张安");
		Role role = new Role(1, "管理员");
		user.setRole(role);
		rs.set("User:100", SerializeUtil.serialize(user));
		User user1 = (User) SerializeUtil.unserialize((byte[]) rs.get("User:100"));
		System.out.println(user1.getRole().getName());
		//rs.del("User:200");
	}

	/**
	 * 单机版Redis操作
	 */
	public static void localhostTest() {
		//连接本地的 Redis 服务
		Jedis jedis = new Jedis("localhost", 6379);
		//选择数据库
		jedis.select(0);
		//查看服务是否运行
		System.out.println("服务正在运行: " + jedis.ping());

		//存储对象
		User user = new User(200, "张安");
		jedis.set("User:200".getBytes(), SerializeUtil.serialize(user));

		//获取对象
		byte[] person = jedis.get(("User:200").getBytes());
		User user1 = (User) SerializeUtil.unserialize(person);
		System.out.println(user1.getName());

		//设置 redis 字符串数据
		jedis.set("runoobkey", "www.runoob.com");
		// 获取存储的数据并输出
		System.out.println("redis 存储的字符串为: " + jedis.get("runoobkey"));

		//存储数据到列表中
		jedis.lpush("site-list", "Runoob");
		jedis.lpush("site-list", "Google");
		jedis.lpush("site-list", "Taobao");
		// 获取存储的数据并输出
		List<String> list = jedis.lrange("site-list", 0, 2);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("列表项为: " + list.get(i));
		}

		// 获取所有键并输出
		//Set<String> keys = jedis.keys("*");
		//Iterator<String> it = keys.iterator();
		//while (it.hasNext()) {
		//	String key = it.next();
		//	System.out.println(key);
		//}

		//必须关闭连接
		jedis.close();
	}
}
