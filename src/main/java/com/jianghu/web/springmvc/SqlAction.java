package com.jianghu.web.springmvc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jianghu.core.Database;
import com.jianghu.core.tools.Log;

/**
 * sql相关Action
 * @author wangjinlong
 *
 */
@Controller
public class SqlAction {
	@RequestMapping("sqlAction/select.htm")
	@ResponseBody
	public String select(HttpServletRequest request, HttpServletResponse response) throws JSONException {
		JSONObject returnObj = new JSONObject();
		try {
			String querySql = request.getParameter("sql");
			querySql += " limit 0,10";
			ResultSet rs = Database.executeQuery(querySql);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			List<String> colList = new ArrayList<String>();
			for (int n = 0; n < rsmd.getColumnCount(); n++) {
				String colsName = rsmd.getColumnName(n + 1);
				colList.add(colsName);
			}
			
			returnObj.put("code", "0");
			returnObj.put("msg", "");
			JSONArray array = new JSONArray();
			int i = 0;
			while(rs.next()) {
				i++;
				JSONObject object = new JSONObject();
				for (String column : colList) {
					object.put(column, rs.getString(column));
				}
				array.put(object);
			}
			returnObj.put("count", i);
			returnObj.put("data", array);
		} catch (Exception e) {
			returnObj.put("code", "500");
			returnObj.put("msg", "查询出错");
			Log.error(e);
		}
		return returnObj.toString();
	}
	
	@RequestMapping("sqlAction/column.htm")
	@ResponseBody
	public String getCloumn(HttpServletRequest request, HttpServletResponse response) throws JSONException {
		String colsStr = "";
		try {
			String querySql = request.getParameter("sql");
			querySql += " limit 0,1";
			ResultSet rs = Database.executeQuery(querySql);
			ResultSetMetaData rsmd = rs.getMetaData();
			JSONArray array = new JSONArray();
			for (int n = 0; n < rsmd.getColumnCount(); n++) {
				String colsName = rsmd.getColumnName(n + 1);
				JSONObject object = new JSONObject();
				object.put("field", colsName);
				object.put("title", colsName);
				array.put(object);
			}
			colsStr = array.toString();
		} catch (Exception e) {
			Log.error(e);
		}
		return colsStr;
	}
	
	@RequestMapping("sqlAction/update.htm")
	@ResponseBody
	public String update(HttpServletRequest request, HttpServletResponse response) throws JSONException {
		int updateColumn = 0;
		try {
			String querySql = request.getParameter("sql");
			updateColumn = Database.executeUpdate(querySql);
		} catch (Exception e) {
			Log.error(e);
		}
		return updateColumn + "";
	}
}
