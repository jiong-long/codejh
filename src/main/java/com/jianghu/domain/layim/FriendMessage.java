package com.jianghu.domain.layim;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "layim_friendmessage")
public class FriendMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cid;//消息主键
	private String username;//消息来源用户名
	private String avatar;//消息来源用户头像
	private int id;//消息的来源ID（如果是私聊，则是用户id，如果是群聊，则是群组id）
	private int toid;//消息的接收者ID（如果是私聊，则是用户id，如果是群聊，则是群组id）
	private String type;//聊天窗口来源类型，从发送消息传递的to里面获取
	private String content;//消息内容
	private boolean mine;//是否我发送的消息，如果为true，则会显示在右方
	private int fromid;//消息的发送者id（比如群组中的某个消息发送者），可用于自动解决浏览器多窗口时的一些问题
	private long timestamp;//服务端时间戳毫秒数

	public FriendMessage() {
		super();
	}

	public FriendMessage(int cid, String username, String avatar, int id, int toid, String type, String content, boolean mine, int fromid, long timestamp) {
		super();
		this.cid = cid;
		this.username = username;
		this.avatar = avatar;
		this.id = id;
		this.toid = toid;
		this.type = type;
		this.content = content;
		this.mine = mine;
		this.fromid = fromid;
		this.timestamp = timestamp;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public int getToid() {
		return toid;
	}

	public void setToid(int toid) {
		this.toid = toid;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean getMine() {
		return mine;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public int getFromid() {
		return fromid;
	}

	public void setFromid(int fromid) {
		this.fromid = fromid;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
