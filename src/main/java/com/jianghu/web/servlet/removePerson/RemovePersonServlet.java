package com.jianghu.web.servlet.removePerson;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jianghu.domain.basic.User;

/**
 * 管理员在线踢人功能
 * 
 * @creatTime 2017年5月29日 下午6:06:23
 * @author jinlong
 * 
 */
public class RemovePersonServlet extends HttpServlet {

	/**
	 * @creatTime 2017年5月29日 下午6:05:51
	 * @author jinlong
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		@SuppressWarnings("unchecked")
		Map<User, HttpSession> map = (Map<User, HttpSession>) this.getServletContext()
				.getAttribute("map");
		for (User user : map.keySet()) {
			if (username.equals(user.getUsername())) {
				HttpSession session = map.get(user);
				session.invalidate();
				break;
			}
		}
		response.sendRedirect("/jiong/page/basic/onlineUserList.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
