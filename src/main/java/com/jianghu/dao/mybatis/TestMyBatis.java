package com.jianghu.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.jianghu.core.MyBatisDB;
import com.jianghu.dao.mybatis.mapper.UserMapper;
import com.jianghu.dao.mybatis.plus.UserPlus;
import com.jianghu.service.mybatis.UserService;

/**
 * MyBatis测试类
 * 
 * @creatTime 2018年9月20日 下午10:38:34
 * @author jinlong
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestMyBatis {
	@Autowired
	private UserService userService;

	/**
	 * 与spring集成版 MyBatis测试
	 * 
	 * @throws Exception
	 */
	@Test
	public void conectSpring() throws Exception {
		//PageHelper原理，拦截要执行的sql，然后重写
		//(页码，每页显示数量)
		PageHelper.startPage(2, 3);
		//排序
		PageHelper.orderBy("id desc");
		List<UserPlus> userList = userService.selectAllUser();
		System.out.println(userList.size());
	}

	/**
	 * 单机版MyBatis测试
	 */
	@Test
	public void selectAllUser() {
		SqlSession session = MyBatisDB.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		try {
			//RowBounds是对ResultSet的分页，大数量时效率较低
			//(偏移量，每页量)与mysql的limit相同
			RowBounds rowBounds = new RowBounds(2, 3);
			List<UserPlus> user = mapper.selectAllUser(rowBounds);
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
