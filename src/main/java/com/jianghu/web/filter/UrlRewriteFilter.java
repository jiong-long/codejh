package com.jianghu.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义UrlRewrite过滤器
 * 
 * @author jinlong
 *
 */
public class UrlRewriteFilter extends org.tuckey.web.filters.urlrewrite.UrlRewriteFilter {
	public UrlRewriteFilter() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
		//放行
		super.doFilter(req, res, filterChain);
	}
}
