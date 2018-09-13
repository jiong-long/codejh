package com.jianghu.web.springmvc;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jianghu.domain.layim.FriendMessage;
import com.jianghu.service.layim.FriendMessageServices;

/**
 * 无法自动注入services时，使用这样的方法调用
 * 
 * @author jinlong
 *
 */
@Component
public class SpringUtil {

	@Autowired
	private FriendMessageServices friendMessageServices;

	public static SpringUtil springUtil;

	@PostConstruct
	public void init() {
		springUtil = this;
	}

	public static void saveFriendMessage(FriendMessage friendMessage) {
		springUtil.friendMessageServices.save(friendMessage);
	}
}
