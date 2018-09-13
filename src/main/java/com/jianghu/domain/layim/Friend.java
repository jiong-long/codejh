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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 好友分组
 * 
 * @author jinlong
 *
 */
@Entity
@Table(name = "layim_friend")
public class Friend {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;//分组ID
	//好友分组名
	private String groupname;
	//分组下的好友列表（多对多）
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "layim_mime_friend",joinColumns={
		@JoinColumn(name="friend_id")
	},inverseJoinColumns = {
		@JoinColumn(name="mine_id")
	})
	private List<Mine> list;
	//该分组属于哪个人（多对一）
	@ManyToOne(fetch = FetchType.LAZY)
	private Mine mine;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public List<Mine> getList() {
		return list;
	}

	public void setList(List<Mine> list) {
		this.list = list;
	}

	public Mine getMine() {
		return mine;
	}

	public void setMine(Mine mine) {
		this.mine = mine;
	}

}
