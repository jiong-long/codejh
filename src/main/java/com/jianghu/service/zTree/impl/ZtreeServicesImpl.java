package com.jianghu.service.zTree.impl;

import java.util.List;

import com.jianghu.dao.hibernate.GenericDAO;
import com.jianghu.domain.zTree.Ztree;
import com.jianghu.service.zTree.ZtreeServices;

/**
 * 目录结构Services实现
 * 
 * @author jinlong
 * 
 */
public class ZtreeServicesImpl implements ZtreeServices {

	private GenericDAO<Ztree> ztreeDAO;

	public GenericDAO<Ztree> getZtreeDAO() {
		return ztreeDAO;
	}

	public void setZtreeDAO(GenericDAO<Ztree> ztreeDAO) {
		this.ztreeDAO = ztreeDAO;
	}

	@Override
	// 查询初始化树节点
	public List<Ztree> getInitZtree() {
		// 使用hql条件查询
		// hql是面向对象的，他引用类名及类的属性名，而不是表名和字段名
		String hql = "from Ztree z where z.pid = ?";
		return ztreeDAO.findByHqlQuery(hql, "0");
	}

	@Override
	// 保存树节点
	public void saveZtree(Ztree ztree) {
		ztreeDAO.save(ztree);
	}

	@Override
	// 修改树节点
	public void updateZtree(Ztree ztree) {
		ztreeDAO.update(ztree);
	}
}
