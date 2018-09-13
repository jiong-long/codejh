package com.jianghu.domain.basic;

import java.util.Date;

/**
 * 首页条目实体类
 * 
 * @author wangjinlong
 * 
 * @creatTime 2017年7月3日 下午11:33:19
 */
public class Item {
	private String id;// 主键
	private String itemName;// 名称
	private String itemArr;// 地址
	private String itemDsc;// 简单描述
	private String itemTxt;// 详细描述
	private Date creatTime;// 创建日期
	private Date updateTime;// 修改日期
	private String img_path;// 图片地址

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemArr() {
		return itemArr;
	}

	public void setItemArr(String itemArr) {
		this.itemArr = itemArr;
	}

	public String getItemDsc() {
		return itemDsc;
	}

	public void setItemDsc(String itemDsc) {
		this.itemDsc = itemDsc;
	}

	public String getItemTxt() {
		return itemTxt;
	}

	public void setItemTxt(String itemTxt) {
		this.itemTxt = itemTxt;
	}

}
