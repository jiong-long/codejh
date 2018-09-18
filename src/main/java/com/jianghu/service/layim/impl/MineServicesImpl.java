package com.jianghu.service.layim.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

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

	//获取个人信息
	public Mine getMineInfo(BigDecimal user_id) {
		return mineDao.findById(Mine.class, user_id.intValue());
	}

	@Override
	public void update(Mine mine) {
		mineDao.update(mine);
	}

}
