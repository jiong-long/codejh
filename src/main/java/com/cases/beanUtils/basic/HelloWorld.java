package com.cases.beanUtils.basic;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

public class HelloWorld {
	public static void main(String[] args) throws Exception {
		//1.得到javaBean的一个字节码对象
		Class<?> clazz = Class.forName("com.jianghu.dao.func.beanUtils.basic.BeanUser");

		//2.生成该字节码的一个对象
		Object obj = clazz.newInstance();

		//3.注册一个日期格式转换器
		DateConverter dateConverter = new DateConverter();
		//设置日期格式
		dateConverter.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
		//注册格式
		ConvertUtils.register(dateConverter, Date.class);

		//4.使用工具对该对象进行赋值
		//注意： 对于基本数据类型，beanutils工具进行自动类型转换。把String自动转成Integer,Double,Float
		BeanUtils.setProperty(obj, "username", "张三");
		BeanUtils.setProperty(obj, "password", "1234");
		//如果要使用特殊的日期类型，则String->Date 不能自动转换,这时候就要注册一个转换器
		BeanUtils.setProperty(obj, "birthday", "1996-06-06");

		System.out.println(obj.toString());
	}
}
