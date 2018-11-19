package com.jianghu.core.func.beanUtils;

import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.poi.ss.formula.functions.T;

/**
 * 封装beanutils工具类
 * 
 * @creatTime 2018年11月18日 下午12:23:23
 * @author jinlong
 */
public class MyBeanUtils {

	/**
	 * 获取request中参数，封装成对象
	 * 
	 * 约定前提： 请求中的参数名称 需要和javabean的属性名称保持一致！！！！
	 * 
	 * @param request
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "hiding" })
	public static <T> T requestToBean(HttpServletRequest request, Class<T> clazz) {
		//创建javaBean对象    
		Object obj = null;
		try {
			obj = clazz.newInstance();
			//处理时间格式
			DateConverter dateConverter = new DateConverter();
			//设置日期格式
			dateConverter.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
			//注册格式
			ConvertUtils.register(dateConverter, Date.class);
			//得到请求中的每个参数
			Enumeration<String> enu = request.getParameterNames();
			while (enu.hasMoreElements()) {
				//获得参数名
				String name = enu.nextElement();
				//获得参数值
				String value = request.getParameter(name);
				//然后把参数拷贝到javaBean对象中
				BeanUtils.setProperty(obj, name, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (T) obj;
	}

	/**
	 * 将一个对象的值复制到另外一个对象中去
	 * 
	 * map中的key要和属性名称一致
	 * 
	 * @param from
	 * @param to
	 * @throws Exception
	 */
	public static void copyObject(T from, T to) throws Exception {
		BeanUtils.copyProperties(to, from);
	}

	/**
	 * 获取map，封装成对象
	 * 
	 * 约定前提： map中的key名称 需要和javabean的属性名称保持一致！！！！
	 * 
	 * @param request
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "hiding" })
	public static <T> T requestToBean(Map<String, Object> map, Class<T> clazz) {
		//创建javaBean对象    
		Object obj = null;
		try {
			obj = clazz.newInstance();
			BeanUtils.copyProperties(obj, map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (T) obj;
	}
}
