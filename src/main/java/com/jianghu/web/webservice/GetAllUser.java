package com.jianghu.web.webservice;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.json.JSONObject;

import com.jianghu.core.tools.WebServiceUtil;

/**
 * restful 风格 raw参数接口
 * 使用 WebServiceUtil.sendPost(String url, String params, Map<String,String> headMap)调用
 * 
 * @author wangjinlong
 * @datetime Jun 1, 2020 11:23:22 AM
 *
 */
@Produces("application/json")
public class GetAllUser {
	@POST
	public String doPost(@Context HttpServletRequest request) {
		JSONObject object = new JSONObject();
		try {
			//获取raw中参数
			String raw = WebServiceUtil.readRaw(request.getInputStream());
			System.out.println(raw);
			
			//获取header中信息
			String username = request.getHeader("username");
			System.out.println(username);
			
			object.put("flag", "S");
			object.put("message", "成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object.toString();
	}
}
