package com.jianghu.service.layim;

import java.math.BigDecimal;

import com.jianghu.domain.layim.Mine;

public abstract interface MineServices {
	//根据usr_id获取个人信息
	public abstract Mine getMineInfo(BigDecimal usr_id);

	//更新mine数据
	public abstract void update(Mine mine);
}
