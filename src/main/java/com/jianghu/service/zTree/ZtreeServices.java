package com.jianghu.service.zTree;

import java.util.List;

import com.jianghu.domain.zTree.Ztree;

/**
 * 目录结构Ztree
 * 
 * @author jinlong
 * 
 */
public interface ZtreeServices {
	// 查询初始化树节点
	public List<Ztree> getInitZtree();

	// 保存树节点
	public void saveZtree(Ztree ztree);

	// 修改树节点
	public void updateZtree(Ztree ztree);
}
