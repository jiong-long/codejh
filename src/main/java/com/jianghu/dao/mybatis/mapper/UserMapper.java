package com.jianghu.dao.mybatis.mapper;

import java.util.List;

import com.jianghu.domain.basic.User;

public interface UserMapper {
	/**
	 * 新增用戶
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int insertUser(User user) throws Exception;

	/**
	 * 修改用戶
	 * 
	 * @param user
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateUser(User user, int id) throws Exception;

	/**
	 * 刪除用戶
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteUser(int id) throws Exception;

	/**
	 * 根据id查询用户信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User selectUserById(int id) throws Exception;

	/**
	 * 查询所有的用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<User> selectAllUser() throws Exception;
}