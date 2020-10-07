package com.jianghu.web.servlet.upLoad;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.Tools;
import com.jianghu.web.servlet.BaseServlet;

/**
 * ocupload Demo
 * 
 * @creatTime 2017年6月17日 下午5:23:14
 * @author jinlong
 * 
 */
public class Upload extends BaseServlet {

	private static final long serialVersionUID = 1L;

	public String ocUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Tools.uploadFile(request);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print("success");
		// 既不转发也不重定向
		return "";
	}
}
