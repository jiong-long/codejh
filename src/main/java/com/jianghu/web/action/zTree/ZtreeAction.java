package com.jianghu.web.action.zTree;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jianghu.core.Database;
import com.jianghu.core.Tools;
import com.jianghu.domain.zTree.Ztree;
import com.jianghu.service.zTree.ZtreeServices;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ZtreeAction extends ActionSupport implements ModelDriven<Ztree> {

	/**
	 * @creatTime 2016年10月12日 下午9:50:25
	 * @author jinlong
	 */
	private static final long serialVersionUID = 1L;
	private Ztree ztree = new Ztree();
	private ZtreeServices ztreeServices;

	public ZtreeServices getZtreeServices() {
		return ztreeServices;
	}

	public void setZtreeServices(ZtreeServices ztreeServices) {
		this.ztreeServices = ztreeServices;
	}

	@Override
	public Ztree getModel() {
		return ztree;
	}

	/**
	 * 获得初始化的树节点
	 * 
	 * @creatTime 2017年5月28日 下午10:07:47
	 * @author jinlong
	 * @return
	 * @throws IOException
	 */
	public String getInitZtree() throws IOException {
		String pid = "0";
		JSONArray array = getTreeArray(pid);
		Tools.returnJSONtoPage(array);
		return NONE;
	}

	// 保存或者修改树节点,需要将节点的ID返回到前台，定位使用
	public String saveOrUpdate() throws IOException, JSONException {
		JSONObject object = new JSONObject();
		String id = "";
		String pid = "";
		if ("".equals(Tools.object2Empty(ztree.getId()))) {// 当主键不存在时，新增
			pid = ztree.getPid();
			String sql = "select max(id) from fc_ztree where pid = '" + pid + "'";
			String maxId = Tools.null2Empty(Database.getUniqueStringValue(sql));
			if ("".equals(maxId)) {// 该节点没有子节点
				id = Integer.parseInt(pid) * 100 + 1 + "";
				ztree.setId(id);
			} else {// 有子节点时
				id = Integer.parseInt(maxId) + 1 + "";
				ztree.setId(id);
			}
			ztreeServices.saveZtree(ztree);
		} else {// 修改
			ztreeServices.updateZtree(ztree);
			id = ztree.getId();
			pid = ztree.getPid();
		}
		object.put("id", id);
		object.put("pid", pid);
		Tools.returnJSONtoPage(object);
		return NONE;
	}

	/**
	 * 获得下级的所有节点
	 * 
	 * @creatTime 2017年5月29日 上午10:44:15
	 * @author jinlong
	 * @return
	 * @throws IOException
	 */
	public String getDownZtree() throws IOException {
		String pid = ztree.getId();
		JSONArray array = getTreeArray(pid);
		Tools.returnJSONtoPage(array);
		return NONE;
	}

	/**
	 * 根据传入的父节点id，获取所有的子节点
	 * 
	 * @creatTime 2017年5月29日 上午10:43:49
	 * @author jinlong
	 * @param pid
	 * @return
	 */
	private JSONArray getTreeArray(String pid) {
		String getOtherSql = "select f.*,(select count(1) from fc_ztree where pid = f.id) downCount from fc_ztree f where pid = ? order by seq";
		ResultSet rs = null;
		JSONArray array = new JSONArray();
		try {
			rs = Database.executeQuery(getOtherSql, pid);
			while (rs.next()) {
				JSONObject object = new JSONObject();
				String id = rs.getString("ID");
				object.put("id", id);
				String name = rs.getString("NAME");
				object.put("name", name);
				String pId = rs.getString("PID");
				object.put("pid", pId);
				String intro = rs.getString("INTRO");
				object.put("intro", intro);
				String seq = rs.getString("SEQ");
				object.put("seq", seq);
				String downCount = rs.getString("downCount");
				if ("0".equals(downCount)) {
					object.put("isParent", "false");
					object.put("icon", "/jiong/images/file.png");
				} else {
					object.put("isParent", "true");
					object.put("iconOpen", "/jiong/images/floder_open.png");
					object.put("iconClose", "/jiong/images/floder_close.png");
				}
				array.put(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Database.closeresouce(rs);
		}
		return array;
	}

	/**
	 * 拖动排序
	 * 
	 * @creatTime 2017年5月29日 上午1:26:08
	 * @author jinlong
	 * @return
	 * @throws Exception
	 */
	public String zTreeOnDrop() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] params = request.getParameter("params").split("@");
		String moveType = params[0];// 移动方式
		String currId = params[1];// 移动节点id
		String pId = params[2];// 目标节点id

		String tableName = "fc_ztree"; // 表名
		String idField = "id"; // id字段
		String pidField = "pid"; // pid字段
		String pxField = "seq"; // 排序字段

		// 核心代码
		if ("prev".equals(moveType)) {//拖拽到目标节点之前，父节点与目标节点相同，排序改为目标节点，目标节点之后的排序依次加1
			//取目标节点的排序与父节点
			String sql = "select NVL(" + pxField + ",0) PX," + pidField + " from " + tableName + " where " + idField + " = ?";
			ResultSet rs = Database.executeQuery(sql, pId);
			String pxSeq = "";
			String pnodeNo = "";
			while (rs.next()) {
				pxSeq = rs.getString("PX");
				pnodeNo = rs.getString(pidField);
			}
			//修改节点的父节点为目标节点的父节点，排序改为目标节点排序
			sql = "update " + tableName + " set " + pxField + " = ?," + pidField + " = ? where " + idField + " = ?";
			Database.executeUpdate(sql, pxSeq, pnodeNo, currId);
			//目标节点之后的排序依次加1（包括目标节点）
			String whereSql = "";
			if ("".equals(pnodeNo)) {//如果父节点为空，则过滤的条件不一样
				whereSql = pidField + " is null";
			} else {
				whereSql = pidField + " = '" + pnodeNo + "'";
			}
			sql = "update " + tableName + " set " + pxField + " = (NVL(" + pxField + ",0) + 1) where " + whereSql + " and NVL(" + pxField + ",0) >= ? and " + idField + " <> ?";
			Database.executeUpdate(sql, pxSeq, currId);
		} else if ("next".equals(moveType)) {//拖拽到目标节点以后，父节点与目标节点相同，排序改为目标节点排序加1，目标节点之后的排序依次加2
			//取目标节点的排序（加1）与父节点
			String sql = "select NVL(" + pxField + ",0)+1 PX," + pidField + " from " + tableName + " where " + idField + " = ?";
			ResultSet rs = Database.executeQuery(sql, pId);
			String pxSeq = "";
			String pnodeNo = "";
			while (rs.next()) {
				pxSeq = rs.getString("PX");
				pnodeNo = rs.getString(pidField);
			}
			//修改节点的父节点为目标节点的父节点，排序为目标节点排序（加1）
			sql = "update " + tableName + " set " + pxField + " = ?," + pidField + " = ? where " + idField + " = ?";
			Database.executeUpdate(sql, new Object[] { pxSeq, pnodeNo, currId });
			//目标节点之后的排序依次依次加1（不包括目标节点）（目标节点排序之前加过1，所以再加1即可）
			sql = "update " + tableName + " set " + pxField + " = (NVL(" + pxField + ",0) + 1) where " + pidField + " = ?" + " and NVL(" + pxField + ",0) >= ? and " + idField
					+ " <> ?";
			Database.executeUpdate(sql, pnodeNo, pxSeq, currId);
		} else if ("inner".equals(moveType)) {//拖拽到目标节点内部，父节点为目标节点，排序改为目标节点最大的排序加1
			String sql = "select NVL(max(" + pxField + "),0)+1 from " + tableName + " where " + pidField + " = ?";
			String pxSeq = Database.getUniqueStringValue(sql, pId);
			sql = "update " + tableName + " set " + pxField + " = ?," + pidField + " = ? where " + idField + " = ?";
			Database.executeUpdate(sql, pxSeq, pId, currId);
		}

		// 返回，用于刷新定位
		JSONObject object = new JSONObject();
		object.put("id", currId);
		object.put("pid", pId);
		object.put("moveType", moveType);
		Tools.returnJSONtoPage(object);
		return NONE;
	}

	/**
	 * 删除节点
	 * 
	 * @creatTime 2017年5月29日 下午12:04:39
	 * @author jinlong
	 * @return
	 * @throws Exception
	 */
	public String deleteNode() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String params = request.getParameter("params");
		// 删除节点，包括子节点
		String deleteSql = "delete from fc_ztree where id in (select id from fc_ztree start with id = ? connect by prior id = pid)";
		Database.executeUpdate(deleteSql, params);
		return NONE;
	}

	// TODO 上级主键按照名称显示
}