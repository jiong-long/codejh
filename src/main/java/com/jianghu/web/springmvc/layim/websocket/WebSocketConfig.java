package com.jianghu.web.springmvc.layim.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * websocket配置类，需要springmvc能够扫描到该类即可
 * 
 * @author jinlong
 *
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// 支持websocket 的访问链接,申明连接的处理类以及拦截器
		registry.addHandler(new WebsocketEndPoint(), "/websocket.htm").addInterceptors(new HandshakeInterceptor());
		// 不支持websocket的访问链接，使用sockjs实现
		// registry.addHandler(new WebsocketEndPoint(),"/sockjs/websocket.htm").addInterceptors(new HandshakeInterceptor()).withSockJS();
	}
}
