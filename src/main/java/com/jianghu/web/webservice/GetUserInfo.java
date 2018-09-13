package com.jianghu.web.webservice;

import java.net.URLDecoder;
import java.sql.ResultSet;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

import org.json.JSONObject;

import com.jianghu.core.Database;
import com.jianghu.core.Tools;

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
			String sql = "select * from bc_user where infactname = ?";
			ResultSet rs = Database.executeQuery(sql, username);
			while (rs.next()) {
				jsonObject.put("id", rs.getString("id"));
				jsonObject.put("username", rs.getString("username"));
				jsonObject.put("infactname", rs.getString("infactname"));
			}
			//对返回值进行编码
			return URLDecoder.decode(jsonObject.toString(), decode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "false";
	}
}
