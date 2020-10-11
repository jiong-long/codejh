package com.common;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware{
	//声明一个静态变量保存
	private static ApplicationContext applicationContext;

	/**
	 * 启动时，会自动注入applicationContext实例
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		 System.out.println("applicationContext正在初始化");
		 SpringUtil.applicationContext = applicationContext;
	}

	/**
	 * 获取对象实例
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

	/**
	 * 获取接口下所有的实例
	 * @param <T>
	 * @param type
	 * @return
	 */
	public static <T> List<T> getByType(final Class<T> type) {
        final String[] names = applicationContext.getBeanNamesForType(type);
        return Stream.of(names).map(name -> applicationContext.getBean(name, type)).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
