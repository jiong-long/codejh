package com.jianghu.web.webservice;

import java.net.URLDecoder;
import java.sql.ResultSet;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

import org.json.JSONObject;

import com.common.EncryptUtil;
import com.common.Tools;
import com.jianghu.dao.Database;

/**
 * restful 风格 params参数接口
 * 使用 WebServiceUtil.sendPost(String url, Map<String, String> paramsMap, Map<String,String> headMap)调用
 * 
 * @author wangjinlong
 * @datetime Jun 1, 2020 11:23:22 AM
 *
 */
@Produces("application/json")
public class GetUserInfo {
	@GET
	public String doGet(@FormParam("username") String username) {
		return doPost(username);
	}

	@POST
	public String doPost(@FormParam("username") String username) {
		if ("".equals(Tools.null2Empty(username))) {
			return "false";
		}
		JSONObject jsonObject = new JSONObject();
		try {
			//对中文参数进行编码
			String decode = Tools.getEncoding(username);
			username = URLDecoder.decode(username, decode);
			String sql = "select * from bc_user where infactname = ? or username = ?";
			ResultSet rs = Database.executeQuery(sql, username, username);
			while (rs.next()) {
				jsonObject.put("id", rs.getString("id"));
				jsonObject.put("username", rs.getString("username"));
				jsonObject.put("infactname", rs.getString("infactname"));
			}
			//对返回值进行编码,加密
			return EncryptUtil.encrypt(URLDecoder.decode(jsonObject.toString(), decode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "false";
	}
}
