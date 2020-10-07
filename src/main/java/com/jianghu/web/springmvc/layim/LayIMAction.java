package com.jianghu.web.springmvc.layim;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Tools;
import com.jianghu.domain.basic.User;
import com.jianghu.domain.layim.Friend;
import com.jianghu.domain.layim.Group;
import com.jianghu.domain.layim.Mine;
import com.jianghu.domain.web.SessionConstants;
import com.jianghu.service.layim.GroupServices;
import com.jianghu.service.layim.MineServices;

/**
 * layIM后台相关方法
 * 
 * @creatTime 2017年10月25日 下午11:18:30
 * @author jinlong
 * 
 */
@Controller
public class LayIMAction {

	@Autowired
	private MineServices mineServices;

	@Autowired
	private GroupServices groupServices;

	/**
	 * 初始化时获取朋友、群等基本信息
	 * 
	 * @creatTime 2017年10月25日 下午11:28:15
	 * @author jinlong
	 * @return
	 */
	@RequestMapping("layIMAction/getBasicInfo.htm")
	// @responseBody注解的作用是将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML
	// 数据，需要注意的呢，在使用此注解之后不会再走试图处理器，而是直接将数据写入到输入流中，他的效果等同于通过response对象输出指定格式的数据。
	@ResponseBody
	public String getBasicInfo(HttpServletRequest request, HttpServletResponse response) {
		JSONObject returnObj = new JSONObject();
		try {
			String code = "0";//0表示成功，其它表示失败
			String msg = "";//失败信息
			//获取登录的用户
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(SessionConstants.USER);
			JSONObject dataArr = new JSONObject();
			if (user != null) {//已登录
				BigDecimal userId = user.getId();
				//NO.1 获取我的信息
				Mine mine = mineServices.getMineInfo(userId);
				JSONObject mineObj = new JSONObject();
				mineObj.put("username", mine.getUsername());//我的昵称
				mineObj.put("id", mine.getId());//我的ID
				mineObj.put("status", mine.getStatus());//在线状态 online：在线、hide：隐身
				mineObj.put("sign", mine.getSign());//我的签名
				mineObj.put("avatar", mine.getAvatar());//我的头像
				dataArr.put("mine", mineObj);

				//NO.2 获取好友列表
				List<Friend> friendList = mine.getFriends();
				JSONArray friendArr = new JSONArray();
				for (Friend friend : friendList) {
					JSONObject friendObj = new JSONObject();
					friendObj.put("groupname", friend.getGroupname());//好友分组名
					friendObj.put("id", friend.getId());//分组ID
					//分组下的好友
					JSONArray friList = new JSONArray();
					List<Mine> mineList = friend.getList();
					for (Mine mine2 : mineList) {
						JSONObject friObj = new JSONObject();
						friObj.put("username", mine2.getUsername());//好友昵称
						friObj.put("id", mine2.getId());//好友ID
						friObj.put("avatar", mine2.getAvatar());//好友头像
						friObj.put("sign", mine2.getSign()); //好友签名
						friObj.put("status", mine2.getStatus());//若值为offline代表离线，online或者不填为在线
						friList.put(friObj);
					}
					friendObj.put("list", friList);
					//将分组放到好友列表集合中
					friendArr.put(friendObj);
				}
				dataArr.put("friend", friendArr);

				//NO.3 获取群组列表
				List<Group> groupList = mine.getGroup();
				JSONArray groupArr = new JSONArray();
				for (Group group : groupList) {
					JSONObject groupObj = new JSONObject();
					groupObj.put("groupname", group.getGroupname());//群组名
					groupObj.put("id", group.getId());//群组ID
					groupObj.put("avatar", group.getAvatar());//群组头像
					groupArr.put(groupObj);
				}
				dataArr.put("group", groupArr);
			} else {//未登录
				return "";
			}
			returnObj.put("code", code);
			returnObj.put("msg", msg);
			returnObj.put("data", dataArr);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return returnObj.toString();
	}

	/**
	 * 上传图片
	 * 
	 * @creatTime 2017年10月26日 下午10:42:10
	 * @author jinlong
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("layIMAction/uploadImg.htm")
	@ResponseBody
	public String uploadImg(HttpServletRequest request, HttpServletResponse response) {
		String src = Tools.uploadFile(request);
		String code = "0";// 0表示上传成功
		String msg = "";// 错误信息
		if (src.startsWith("1")) {// 上传失败
			code = "1";
			msg = src.substring(1);
			src = "";
		}
		JSONObject returnObject = new JSONObject();
		try {
			returnObject.put("code", code);
			returnObject.put("msg", msg);
			JSONObject dataObject = new JSONObject();
			dataObject.put("src", "\\jiong" + src.split("jiong")[1]);
			returnObject.put("data", dataObject);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return returnObject.toString();
	}

	/**
	 * 上传文件
	 * 
	 * @creatTime 2017年10月26日 下午10:42:10
	 * @author jinlong
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("layIMAction/uploadFile.htm")
	@ResponseBody
	public String uploadFile(HttpServletRequest request, HttpServletResponse response) {
		String src = Tools.uploadFile(request);// 文件url
		String code = "0";// 0表示上传成功
		String msg = "";// 错误信息
		if (src.startsWith("1")) {// 上传失败
			code = "1";
			msg = src.substring(1);
			src = "";
		}
		JSONObject returnObject = new JSONObject();
		try {
			returnObject.put("code", code);
			returnObject.put("msg", msg);
			JSONObject dataObject = new JSONObject();
			dataObject.put("src", "\\jiong" + src.split("jiong")[1]);
			dataObject.put("name", src.substring(src.lastIndexOf("\\") + 1));
			returnObject.put("data", dataObject);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return returnObject.toString();
	}

	/**
	 * 获取消息盒子数据
	 * 
	 * @creatTime 2017年10月31日 下午11:10:11
	 * @author jinlong
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "layIMAction/getMsgBox.htm")
	@ResponseBody
	public String getMsgBox(HttpServletRequest request, HttpServletResponse response) {
		String json = "{\"code\":0,\"pages\":1,\"data\":[{\"id\":76,\"content\":\"申请添加你为好友\",\"uid\":168,\"from\":166488,\"from_group\":0,\"type\":1,\"remark\":\"有问题要问\",\"href\":null,\"read\":1,\"time\":\"刚刚\",\"user\":{\"id\":166488,\"avatar\":\"http://q.qlogo.cn/qqapp/101235792/B704597964F9BD0DB648292D1B09F7E8/100\",\"username\":\"李彦宏\",\"sign\":null}},{\"id\":75,\"content\":\"申请添加你为好友\",\"uid\":168,\"from\":347592,\"from_group\":0,\"type\":1,\"remark\":\"你好啊！\",\"href\":null,\"read\":1,\"time\":\"刚刚\",\"user\":{\"id\":347592,\"avatar\":\"http://q.qlogo.cn/qqapp/101235792/B78751375E0531675B1272AD994BA875/100\",\"username\":\"麻花疼\",\"sign\":null}},{\"id\":62,\"content\":\"雷军拒绝了你的好友申请\",\"uid\":168,\"from\":null,\"from_group\":null,\"type\":1,\"remark\":null,\"href\":null,\"read\":1,\"time\":\"10天前\",\"user\":{\"id\":null}},{\"id\":60,\"content\":\"马小云已经同意你的好友申请\",\"uid\":168,\"from\":null,\"from_group\":null,\"type\":1,\"remark\":null,\"href\":null,\"read\":1,\"time\":\"10天前\",\"user\":{\"id\":null}},{\"id\":61,\"content\":\"贤心已经同意你的好友申请\",\"uid\":168,\"from\":null,\"from_group\":null,\"type\":1,\"remark\":null,\"href\":null,\"read\":1,\"time\":\"10天前\",\"user\":{\"id\":null}}]}";
		return json;
	}

	/**
	 * 获取群组好友列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "layIMAction/getGroupPer.htm")
	@ResponseBody
	public String getGroupPer(HttpServletRequest request, HttpServletResponse response) {
		String group_id = request.getParameter("id");
		JSONObject returnObj = new JSONObject();
		try {
			Group group = groupServices.getGroupById(group_id);
			String code = "0";
			String msg = "";

			List<Mine> mineList = group.getList();
			JSONArray list = new JSONArray();
			for (Mine mine : mineList) {
				JSONObject mineObj = new JSONObject();
				mineObj.put("username", mine.getUsername());
				mineObj.put("id", mine.getId());
				mineObj.put("avatar", mine.getAvatar());
				mineObj.put("sign", mine.getSign());
				list.put(mineObj);
			}
			JSONObject data = new JSONObject();
			data.put("list", list);

			returnObj.put("code", code);
			returnObj.put("msg", msg);
			returnObj.put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnObj.toString();
	}

	/**
	 * 更新在线状态
	 * 
	 * @param request
	 * @param response
	 * @param status
	 */
	@RequestMapping(value = "layIMAction/changeState.htm")
	@ResponseBody
	public void changeState(HttpServletRequest request, HttpServletResponse response, String status) {
		User user = (User) request.getSession().getAttribute(SessionConstants.USER);
		Mine mine = user.getMine();
		mine.setStatus(status);
		mineServices.update(mine);
	}

	/**
	 * 更换签名
	 * 
	 * @param request
	 * @param response
	 * @param status
	 */
	@RequestMapping(value = "layIMAction/changeSign.htm")
	@ResponseBody
	public void changeSign(HttpServletRequest request, HttpServletResponse response, String value) {
		User user = (User) request.getSession().getAttribute(SessionConstants.USER);
		Mine mine = user.getMine();
		mine.setSign(value);
		mineServices.update(mine);
	}
}
