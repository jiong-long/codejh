package com.jianghu.domain.basic;

import java.math.BigDecimal;

/**
 * sequence实体类
 * 
 * @author wangjinlong
 * 
 * @creatTime 2017年7月2日 上午9:18:05
 */
public class Sequence {
	private String seq_nam;// 序列名称
	private BigDecimal curr_val;// 当前值

	// 默认开始值1，最大值99999999，步长1，开始于1，不缓存，不循环

	public String getSeq_nam() {
		return seq_nam;
	}

	public BigDecimal getCurr_val() {
		return curr_val;
	}

	public void setCurr_val(BigDecimal curr_val) {
		this.curr_val = curr_val;
	}

	public void setSeq_nam(String seq_nam) {
		this.seq_nam = seq_nam;
	}
}
