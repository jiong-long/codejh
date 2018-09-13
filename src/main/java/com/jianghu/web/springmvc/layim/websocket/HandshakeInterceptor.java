package com.jianghu.web.springmvc.layim.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.jianghu.domain.basic.User;
import com.jianghu.domain.web.SessionConstants;

/**
 * 在websocket连接成功前和成功后增加一些额外的功能
 * 
 * @author jinlong
 *
 */
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		//握手前，从session中取到userId存到attributes中去
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			if (session != null) {
				User user = (User) session.getAttribute(SessionConstants.USER);
				if (user != null) {
					//websocket中将mine的id存入
					attributes.put(SessionConstants.WEBSOCKET_USER_ID, user.getMine().getId());
				}
			}
		}
		return super.beforeHandshake(request, response, wsHandler, attributes);
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
		super.afterHandshake(request, response, wsHandler, ex);
	}

}
