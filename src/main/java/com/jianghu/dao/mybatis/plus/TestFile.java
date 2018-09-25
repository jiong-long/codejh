package com.jianghu.dao.mybatis.plus;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * MyBatis Plus测试类
 * 
 * @author wangjinlong
 * @createTime 2018年9月24日 上午11:56:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:mybatisPlus-config.xml" })
public class TestFile {
	@Autowired
	private UserMapperPlus userMapperPlus;

	@Test
	public void testSelect() {
		List<UserPlus> userList = userMapperPlus.selectList(null);
		System.out.println(userList.size());
	}
}
