package com.jianghu.domain.layim;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jianghu.domain.basic.User;


/**
 * 人员基本信息
 * 
 * @author jinlong
 *
 */
@Entity
@Table(name = "layim_mime")
public class Mine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;//我的ID
	//我的昵称
	private String username;
	//在线状态 online：在线、hide：隐身
	private String status;
	//我的签名
	private String sign;
	//我的头像
	private String avatar;

	//关联系统用户（一对一）
	@OneToOne(fetch = FetchType.LAZY)
	//不写的话会默认添加一个字段
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToMany(mappedBy = "list", fetch = FetchType.LAZY)
	private List<Friend> friend;//该用户属于那些分组

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "layim_mime_group", joinColumns = { @JoinColumn(name = "mime_id") }, inverseJoinColumns = { @JoinColumn(name = "group_id") })
	private List<Group> group;//该用户属于哪些群组（多对多）

	@OneToMany(mappedBy = "list", fetch = FetchType.LAZY)
	private List<Friend> friends;//该用户有哪些分组

	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}

	public List<Group> getGroup() {
		return group;
	}

	public void setGroup(List<Group> group) {
		this.group = group;
	}

	public List<Friend> getFriend() {
		return friend;
	}

	public void setFriend(List<Friend> friend) {
		this.friend = friend;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
