package com.jianghu.service.layim.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianghu.dao.hibernate.GenericDAO;
import com.jianghu.domain.layim.Mine;
import com.jianghu.service.layim.MineServices;

@Service("mineServices")
@Transactional
public class MineServicesImpl implements MineServices {

	@Resource(name = "mineDao")
	private GenericDAO<Mine> mineDao;

	// 获取个人信息
	@Override
	public Mine getMineInfo(BigDecimal user_id) {
		// 根据用户ID获取到对应的信息
		DetachedCriteria criteria = DetachedCriteria.forClass(Mine.class);
		// user.id 和 user_id 的数据类型要一致
		criteria.add(Restrictions.eq("user.id", user_id));
		List<Mine> mineList = mineDao.findByCriteria(criteria);
		return mineList.get(0);
	}

	@Override
	public void update(Mine mine) {
		mineDao.update(mine);
	}

}
