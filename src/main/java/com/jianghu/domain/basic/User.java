package com.jianghu.domain.basic;

import java.math.BigDecimal;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.jianghu.domain.layim.Mine;


/**
 * 用户
 * 
 * @author jinlong
 * 
 */

@Entity
@Table(name = "bc_user")
public class User implements HttpSessionBindingListener {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigDecimal id;
	private String username;
	private String password;
	//该字段不存到数据库中
	@Transient
	private String autoLogin;
	private String infactname;
	@Transient
	private String firstcode;

	//mappedBy标签一定是定义在被拥有方的，他指向拥有方,ManyToOne不存在该属性;
	//cascade默认值none
	//fetch默认值FetchType EAGER
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	private Mine mine;//关联的layim用户（一对一）

	public Mine getMine() {
		return mine;
	}

	public void setMine(Mine mine) {
		this.mine = mine;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(String autoLogin) {
		this.autoLogin = autoLogin;
	}

	public String getInfactname() {
		return infactname;
	}

	public void setInfactname(String infactname) {
		this.infactname = infactname;
	}

	public String getFirstcode() {
		return firstcode;
	}

	public void setFirstcode(String firstcode) {
		this.firstcode = firstcode;
	}

	/**
	 * 监听器，监听用户创建
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void valueBound(HttpSessionBindingEvent arg0) {
		HttpSession session = arg0.getSession();
		ServletContext context = session.getServletContext();
		Map<User, HttpSession> map = (Map<User, HttpSession>) context.getAttribute("map");
		if (map != null) {
			map.put(this, session);
			System.out.println("创建了用户");
		} else {
			System.out.println("创建用户时，map为空");
		}
	}

	/**
	 * 监听器，监听用户销毁
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		HttpSession session = arg0.getSession();
		ServletContext context = session.getServletContext();
		Map<User, HttpSession> map = (Map<User, HttpSession>) context.getAttribute("map");
		map.remove(this);
		System.out.println("销毁了用户");
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", infactname=" + infactname + "]";
	}
}
