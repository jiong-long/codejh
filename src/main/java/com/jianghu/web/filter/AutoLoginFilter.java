package com.jianghu.web.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.jianghu.core.Database;
import com.jianghu.core.Tools;
import com.jianghu.domain.basic.User;
import com.jianghu.domain.web.SessionConstants;

/**
 * 自动登录
 * 
 * @creatTime 2017年5月19日 下午11:28:09
 * @author jinlong
 * 
 */
public class AutoLoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletrequest;
		if (request.getSession().getAttribute(SessionConstants.USER) == null) {// 未登录
			Cookie cookie = Tools.findCookieByName(request.getCookies(), "autologin");
			if (cookie == null) {// 不能自动登录
				filterchain.doFilter(request, servletresponse);
			} else {// 需要自动登录
				String username = cookie.getValue().split("\\.")[0];
				username = URLDecoder.decode(username, "utf-8");
				// cookie中存放的是密文
				String password = cookie.getValue().split("\\.")[1];
				String sql = "select * from bc_user where username = ? and password = ?";
				try {
					List<User> user = Database.executeQuery(sql, User.class, username, password);
					if (user.size() > 0) {
						request.getSession().setAttribute(SessionConstants.USER, user.get(0));
					}
					filterchain.doFilter(request, servletresponse);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {// 已登录
			filterchain.doFilter(request, servletresponse);
		}
	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
	}
}
