package com.jianghu.web.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

import com.jianghu.domain.basic.User;

/**
 * 监听用户创建于销毁，判断用户是否在线
 * 
 * @creatTime 2017年6月3日 上午12:03:33
 * @author jinlong
 * 
 */
public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Map<User, HttpSession> map = new HashMap<User, HttpSession>();
		ServletContext context = arg0.getServletContext();
		context.setAttribute("map", map);
		System.out.println("初始化了监听器");
	}

}
