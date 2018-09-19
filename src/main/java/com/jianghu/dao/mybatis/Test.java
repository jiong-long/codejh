package com.jianghu.dao.mybatis;

import org.apache.ibatis.session.SqlSession;

import com.jianghu.core.MyBatisDB;
import com.jianghu.domain.basic.User;

public class Test {
	public static void main(String[] args) {
		selectUserById();
	}

	/**
	 * 根据id查询用户
	 */
	private static void selectUserById() {
		SqlSession session = MyBatisDB.getSession();
		try {
			User user = session.selectOne("com.jianghu.dao.mybatis.xml.UserMapper.selectUserById", 1);
			System.out.println(user.toString());
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}
}
