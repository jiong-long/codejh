package com.jianghu.web.action.angularJS;

import java.io.IOException;

import com.common.Tools;
import com.opensymphony.xwork2.ActionSupport;

/**
 * angularJS http服务测试
 * 
 * @creatTime 2016年11月20日 下午4:01:05
 * @author jinlong
 * 
 */
public class AngularJSAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	public String getAlldata() {
		String returnStr = "{ \"sites\": [ "
				+ " { \"Name\": \"菜鸟教程\", \"Url\": \"www.runoob.com\", \"Country\": \"CN\" },"
				+ " { \"Name\": \"Google\", \"Url\": \"www.google.com\", \"Country\": \"USA\" }, "
				+ " { \"Name\": \"Facebook\", \"Url\": \"www.facebook.com\", \"Country\": \"USA\" }, "
				+ " { \"Name\": \"微博\", \"Url\": \"www.weibo.com\", \"Country\": \"CN\" } ] }";
		try {
			Tools.returnJSONtoPage(returnStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
}
