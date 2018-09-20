package com.jianghu.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jianghu.core.MyBatisDB;
import com.jianghu.dao.mybatis.mapper.UserMapper;
import com.jianghu.domain.basic.User;

/**
 * MyBatis测试类
 * 
 * @creatTime 2018年9月20日 下午10:38:34
 * @author jinlong
 */
public class Test {
	public static void main(String[] args) {
		//配置文件模式
		selectUserById();
		//接口模式
		selectAllUser();
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

	/**
	 * 查询所有的用户
	 */
	private static void selectAllUser() {
		SqlSession session = MyBatisDB.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		try {
			List<User> user = mapper.selectAllUser();
			System.out.println(user.size());
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}
}
