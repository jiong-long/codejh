package com.jianghu.domain.layim;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 群组
 * 
 * @author jinlong
 *
 */
@Entity
@Table(name = "layim_group")
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;//群组ID
	//群组名
	private String groupname;
	//群组头像
	private String avatar;
	
	//该群组中有哪些人(多对多)
	@ManyToMany(mappedBy = "group",fetch = FetchType.LAZY)
	private List<Mine> list;

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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<Mine> getList() {
		return list;
	}

	public void setList(List<Mine> list) {
		this.list = list;
	}

}
