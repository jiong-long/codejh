package com.jianghu.web.action.basic;

import java.io.File;
import java.io.IOException;
import java.sql.Clob;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import com.jianghu.core.Database;
import com.jianghu.core.Tools;
import com.jianghu.core.func.SequenceThread;
import com.jianghu.core.tools.FileUtil;
import com.jianghu.core.tools.LuceneUtil;
import com.jianghu.domain.basic.Item;
import com.jianghu.domain.lucene.LuceneField;
import com.jianghu.domain.lucene.LuceneThread;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 首页条目管理action
 * 
 * @author wangjinlong
 * 
 * @creatTime 2017年7月4日 下午8:50:19
 */
public class ItemAction extends ActionSupport implements ModelDriven<Item> {

	private static final long serialVersionUID = 1L;

	private Item item = new Item();
	
	//线程池
	private static ExecutorService luceneExecutor = Executors.newFixedThreadPool(3);

	@Override
	public Item getModel() {
		return item;
	}

	// 附件上传必要的三个属性
	private File myUpload;
	// 可以不加这句的，略微有点强迫症
	@SuppressWarnings("unused")
	private String myUploadContentType;
	private String myUploadFileName;

	public void setMyUpload(File myUpload) {
		this.myUpload = myUpload;
	}

	public void setMyUploadContentType(String myUploadContentType) {
		this.myUploadContentType = myUploadContentType;
	}

	public void setMyUploadFileName(String myUploadFileName) {
		this.myUploadFileName = myUploadFileName;
	}

	/**
	 * 保存或者修改
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年7月5日 下午11:00:01
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdate() throws Exception {
		String id = item.getId();
		LuceneUtil luceneUtil = LuceneUtil.instance();
		if (Tools.isEmpty(id)) {// 如果为空，保存
			String saveSql = "insert into bc_item(ID,ITEMNAME,ITEMARR,ITEMDSC,ITEMTXT,CREATTIME,UPDATETIME,SEECOUNT,IMG_PATH) values(?,?,?,?,?,sysdate(),sysdate(),0,?)";
			id = SequenceThread.instance("bc_item").getNextVal();
			Database.executeUpdate(saveSql, id, item.getItemName(), item.getItemArr(), item.getItemDsc(),
					item.getItemTxt(), item.getImg_path());
			// 添加索引到索引库
			LuceneField luceneField = new LuceneField(Long.parseLong(id), item.getItemName(), item.getItemDsc());
			luceneUtil.writeIndex(luceneField);
		} else {// 修改
			String updateSql = "update bc_item set ITEMNAME = ?,ITEMARR = ?,ITEMDSC = ?,ITEMTXT = ?,UPDATETIME = sysdate(),IMG_PATH = ? where id = ?";
			Database.executeUpdate(updateSql, item.getItemName(), item.getItemArr(), item.getItemDsc(),
					item.getItemTxt(), item.getImg_path(), id);
			// 更新到索引库
			LuceneField luceneField = new LuceneField(Long.parseLong(id), item.getItemName(), item.getItemDsc());
			luceneUtil.updateDocument(luceneField);
		}
		Tools.returnJSONtoPage(getJsonData(id));
		return null;
	}

	/**
	 * 上传图片
	 * 
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	public String upload() throws IOException, JSONException {
		// 获得到本地磁盘的真实路径
		String savePath = ServletActionContext.getServletContext().getRealPath("/images/item");
		savePath = savePath + "\\" + myUploadFileName;
		if (myUpload == null) {
			return null;
		}
		FileUtil.copyFile(myUpload, savePath);
		JSONObject object = new JSONObject();
		object.put("fileName", myUploadFileName);
		Tools.returnJSONtoPage(object);
		return null;
	}

	/**
	 * 删除Item
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年7月30日 下午9:30:26
	 * @return
	 * @throws Exception
	 */
	public String deleteItem() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		String deleteSql = "delete from bc_item where id = ?";
		Database.executeUpdate(deleteSql, id);
		// 删除对应的索引
		LuceneUtil luceneUtil = LuceneUtil.instance();
		luceneUtil.deleteIndexById(id);
		return null;
	}

	/**
	 * 根据Id获取相关信息
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年7月16日 下午11:14:38
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	public String showData() throws IOException, Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		// 查看了相关的信息，则访问次数加1
		String updateSql = "update bc_item set SEECOUNT = SEECOUNT + 1 where id = ?";
		Database.executeUpdate(updateSql, id);
		Tools.returnJSONtoPage(getJsonData(id));
		return null;
	}

	/**
	 * 获取item的值，以json的格式返回到前台展示
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年7月5日 下午11:00:24
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public JSONObject getJsonData(String id) throws Exception {
		String getSql = "select * from bc_item where id = ?";
		ResultSet rs = Database.executeQuery(getSql, id);
		JSONObject object = new JSONObject();
		while (rs.next()) {
			object.put("ID", rs.getString("ID"));
			object.put("ITEMNAME", Tools.null2Empty(rs.getString("ITEMNAME")));
			object.put("ITEMARR", Tools.null2Empty(rs.getString("ITEMARR")));
			object.put("ITEMDSC", Tools.null2Empty(rs.getString("ITEMDSC")));
			// Java对Clob的操作
			Clob clob = rs.getClob("ITEMTXT");
			if (!Tools.isEmpty(clob) && clob.length() > 1) {
				object.put("ITEMTXT", clob.getSubString(1, (int) clob.length()));
			}
			object.put("CREATTIME", Tools.null2Empty(rs.getString("CREATTIME")));
			object.put("UPDATETIME", Tools.null2Empty(rs.getString("UPDATETIME")));
			String img_path = Tools.null2Empty(rs.getString("IMG_PATH"));
			String full_path = "";
			if (!"".equals(img_path)) {
				full_path = "/jiong/images/item/" + img_path;
			}
			object.put("IMG_PATH", img_path);
			object.put("FULL_PATH", full_path);
		}
		return object;
	}

	/**
	 * 更新索引
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年9月30日 下午11:43:45
	 */
	public String updateLucene() {
		updateLucenePublic();
		return null;
	}

	public static void updateLucenePublic() {
		String getSql = "select * from bc_item";
		ResultSet rs;
		try {
			rs = Database.executeQuery(getSql);
			List<LuceneField> list = new ArrayList<LuceneField>();
			while (rs.next()) {
				LuceneField luceneField = new LuceneField(rs.getLong("ID"), rs.getString("ITEMNAME"),
						rs.getString("ITEMDSC"));
				list.add(luceneField);
			}
			// 通过线程来更新索引
			// LuceneThread luceneThread = new LuceneThread(list);
			// Thread thread = new Thread(luceneThread);
			// thread.start();// 不要使用luceneThread.run();（这个是普通的方法，没有启动线程）
			luceneExecutor.submit(new LuceneThread(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 实现ActionSupport中的三个方法，可以将struts中的错误信息打印出来
	 */
	@Override
	public void addActionError(String errorMsg) {
		System.out.println(errorMsg);
	}

	@Override
	public void addActionMessage(String msg) {
		System.out.println(msg);
	}

	@Override
	public void addFieldError(String fieldName, String errorMessage) {
		System.out.println(fieldName);
		System.out.println(errorMessage);
	}
}
