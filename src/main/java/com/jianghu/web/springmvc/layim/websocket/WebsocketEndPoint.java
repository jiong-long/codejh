package com.jianghu.web.springmvc.layim.websocket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.jianghu.core.Database;
import com.jianghu.core.func.SequenceThread;
import com.jianghu.domain.layim.FriendMessage;
import com.jianghu.domain.web.SessionConstants;
import com.jianghu.web.springmvc.SpringUtil;

/**
 * websocket处理类
 * 
 * @author jinlong
 *
 */
public class WebsocketEndPoint extends TextWebSocketHandler {
	public static Map<String, WebSocketSession> usersMap;// key是mine的id
	static {
		usersMap = new HashMap<String, WebSocketSession>();
	}

	/**
	 * 与客户端完成连接后调用，会触发js中socket.onopen()方法
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Object usr_id = session.getAttributes().get(SessionConstants.WEBSOCKET_USER_ID);
		if (usr_id != null) {
			usersMap.put(usr_id.toString(), session);
		}
	}

	/**
	 * 在js中调用socket.send()时候，会调用该方法
	 * 
	 * @throws Exception
	 */
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		FriendMessage friendMessage = getFriendMessage(message);
		TextMessage sendMsg = changeToTextMsg(message, friendMessage);
		// 获取接收方的id（如果是私聊，则是用户id，如果是群聊，则是群组id）
		String toId = friendMessage.getToid() + "";
		String type = friendMessage.getType();
		if ("friend".equals(type)) {// 好友消息
			sendMessageToUser(toId, sendMsg);
		} else {// 群消息
			String fromId = friendMessage.getFromid() + "";
			sendMessageToUsers(toId, fromId, sendMsg);
		}
	}

	public TextMessage changeToTextMsg(TextMessage textMessage, FriendMessage friendMessage)
			throws JSONException, UnsupportedEncodingException {
		String messageStr = textMessage.getPayload();
		JSONObject object = new JSONObject(messageStr);
		String type = object.getString("type");
		JSONObject dataJson = new JSONObject();
		dataJson.put("username", friendMessage.getUsername());
		dataJson.put("avatar", friendMessage.getAvatar());
		dataJson.put("id", friendMessage.getId());
		dataJson.put("type", friendMessage.getType());
		dataJson.put("content", friendMessage.getContent());
		dataJson.put("cid", friendMessage.getCid());
		dataJson.put("mine", friendMessage.getMine());
		dataJson.put("fromid", friendMessage.getFromid());
		dataJson.put("timestamp", friendMessage.getTimestamp());
		JSONObject returnJson = new JSONObject();
		returnJson.put("type", type);
		returnJson.put("data", dataJson);
		byte[] payload = returnJson.toString().getBytes("UTF-8");
		TextMessage message = new TextMessage(payload);
		return message;
	}

	/**
	 * 将获取到的消息封装成FriendMessage对象
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public FriendMessage getFriendMessage(TextMessage message) throws Exception {
		String messageStr = message.getPayload();
		JSONObject object = new JSONObject(messageStr);// 获取发送的消息
		JSONObject dataObject = object.getJSONObject("data");
		JSONObject mineObject = dataObject.getJSONObject("mine");
		JSONObject toObject = dataObject.getJSONObject("to");
		int cid = Integer.parseInt(SequenceThread.instance("FRIENDMESSAGE_SEQ").getNextVal());
		String username = mineObject.getString("username");
		String avatar = mineObject.getString("avatar");
		int id = mineObject.getInt("id");
		int toid = toObject.getInt("id");
		String type = toObject.getString("type");
		String content = mineObject.getString("content");
		boolean mine = false;// mineObject.getBoolean("mine");
		int fromid = mineObject.getInt("id");
		long timestamp = System.currentTimeMillis();
		FriendMessage friendMessage = new FriendMessage(cid, username, avatar, id, toid, type, content, mine, fromid,
				timestamp);
		SpringUtil.saveFriendMessage(friendMessage);
		return friendMessage;
	}

	/**
	 * 给某个用户发送消息
	 * 
	 * @param userName
	 * @param message
	 * @throws IOException
	 */
	public void sendMessageToUser(String userId, TextMessage message) throws IOException {
		Set<String> userIdSet = usersMap.keySet();
		for (String user_id : userIdSet) {
			if (userId.equals(user_id)) {
				WebSocketSession socketSession = usersMap.get(user_id);
				if (socketSession.isOpen()) {
					socketSession.sendMessage(message);
				}
			}
		}
	}

	/**
	 * 给在线的群用户发送消息
	 * 
	 * @param message
	 *            发送的消息
	 * @throws Exception
	 */
	public void sendMessageToUsers(String groupId, String fromId, TextMessage message) throws Exception {
		String getGroupUserId = "select mime_id from layim_mime_group where group_id = ? and mime_id <> ?";
		ResultSet rs = Database.executeQuery(getGroupUserId, groupId, fromId);
		while (rs.next()) {
			String groupUserId = rs.getString("mime_id");
			WebSocketSession socketSession = usersMap.get(groupUserId);
			if (socketSession != null) {
				if (socketSession.isOpen()) {
					socketSession.sendMessage(message);
				}
			}
		}

	}

	/**
	 * 消息传输出错时调用，会触发js中socket.onerror()方法
	 */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		Object usr_id = session.getAttributes().get(SessionConstants.WEBSOCKET_USER_ID);
		if (usr_id != null) {
			usersMap.remove(usr_id);
		}
		System.out.println("websocket连接出错：" + exception.getMessage());
	}

	/**
	 * 一个客户端连接断开时关闭
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		Object usr_id = session.getAttributes().get(SessionConstants.WEBSOCKET_USER_ID);
		if (usr_id != null) {// 已登录
			usersMap.remove(usr_id);
		}
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
