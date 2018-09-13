package com.jianghu.web.servlet.upLoad;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.multipart.JakartaMultiPartRequest;

/**
 * struts2会拦截所有的请求（配置的为/*的时候） struts2为简化上传功能，把所有的enctype="multipart/form-data"
 * 表单做了wrapper最后把HttpServletResquest封装成
 * org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper
 * 导致原生的fileUpload.parseRequest(request)无法获取到值，如果不使用struts2的上传，
 * 需要根据URI来判断是否需要封装HttpServletRequest,需要在struts.xml中配置
 * 
 * @creatTime 2017年6月4日 上午11:10:16
 * @author jinlong
 * 
 */
public class MyMultiPartRequest extends JakartaMultiPartRequest {
	@Override
	public void parse(HttpServletRequest request, String arg1) throws IOException {
		// 取得请求的URL
		String url = request.getRequestURI().toString();
		if (url.indexOf(".do") > 0) {// 需要使用Struts2的上传时
			super.parse(request, arg1);
		}
	}
}
