package com.jianghu.dao.mybatis.plus;

import java.math.BigDecimal;

import lombok.Data;

/**
 * MyBatis Plus实体类
 * 
 * @author wangjinlong
 * @createTime 2018年9月24日 上午11:57:16
 */
@Data
public class UserPlus {
	private BigDecimal id;
	private String username;
	private String password;
	private String infactname;
}
