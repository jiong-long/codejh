package com.jianghu.core;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisDB {
	public static SqlSessionFactory sessionFactory;

	static {
		try {
			//使用MyBatis提供的Resources类加载mybatis的配置文件
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//创建能执行映射文件中sql的sqlSession
	public static SqlSession getSession() {
		return sessionFactory.openSession();
	}
}
