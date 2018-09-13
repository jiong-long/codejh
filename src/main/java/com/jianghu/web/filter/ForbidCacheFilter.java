package com.jianghu.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 禁用缓存
 * 
 * @creatTime 2017年5月13日 下午3:19:19
 * @author jinlong
 * 
 */
public class ForbidCacheFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse,
			FilterChain filterchain) throws IOException, ServletException {
		// 默认禁用所有的jsp以及servlet缓存
		HttpServletResponse response = (HttpServletResponse) servletresponse;
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", -1);
		response.setHeader("Pragma", "no-cache");
		filterchain.doFilter(servletrequest, response);
	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
	}

}
