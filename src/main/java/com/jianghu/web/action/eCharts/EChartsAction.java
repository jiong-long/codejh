package com.jianghu.web.action.eCharts;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.jianghu.core.Database;
import com.jianghu.core.Tools;
import com.jianghu.domain.eCharts.ECharts;
import com.opensymphony.xwork2.ActionSupport;

/**
 * EChartsAction
 * 
 * @creatTime 2016年10月12日 下午9:50:36
 * @author jinlong
 * 
 */
public class EChartsAction extends ActionSupport {
	/**
	 * @creatTime 2016年10月12日 下午9:47:57
	 * @author jinlong
	 */
	private static final long serialVersionUID = 1L;

	public String getAlldata() {
		ResultSet rs = null;
		try {
			String sql = "select NAME from echarts where selected = 1";
			List<ECharts> list = new ArrayList<ECharts>();
			rs = Database.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("NAME");
				ECharts eCharts = new ECharts();
				eCharts.setName(name);
				eCharts.setSelected(true);
				list.add(eCharts);
			}
			// 序列化JSON数据
			Tools.serializerObject(list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Database.closeresouce(rs);
		}
		return null;
	}

	public String saveData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("key");
		String sql = "select selected from echarts where name = '" + name + "'";
		try {
			String selected = Database.getUniqueStringValue(sql);
			if (selected == null || "".equals(selected)) {
				// 数据不存在时，将数据插入，并将选中状态置为1
				sql = "insert into echarts values('" + name + "','1')";
			} else if ("0".equals(selected)) {
				// 改变状态为选中
				sql = "update echarts set selected = 1 where name = '" + name + "'";
			} else {
				// 改变状态为未选中
				sql = "update echarts set selected = 0 where name = '" + name + "'";
			}
			Database.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
}
