package com.jianghu.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 演示BaseServlet如何使用
 * 
 * 继承BaseServlet而不是HttpServlet
 * 
 * @creatTime 2017年6月2日 下午11:53:04
 * @author jinlong
 * 
 */
public class DemoServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 请求处理方法的参数都与doGet()和doPost()相同，即request和response
	 * 但请求处理方法有String类型的返回值，而doGet()和doPost()没有返回值。 在请求本方法时需要给出method=regist参数！
	 */

	// 访问本方法的URL为:http://localhost:7001/jiong/AServlet?method=add
	// 在表单中访问需要在form标签后<input type="hidden" name="method" value="add"/>

	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("add()...");
		String name = request.getParameter("username");
		System.out.println("name: " + name);
		// 转发到/index.jsp页面
		return "f:/index.jsp";
	}

	// 访问本方法的URL为http://localhost:7001/jiong/AServlet?method=update
	public String update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("update()...");
		// 重定向到/index.jsp 
		return "r:/index.jsp";
		// null：表示不转发也不重定向；
	}
}
