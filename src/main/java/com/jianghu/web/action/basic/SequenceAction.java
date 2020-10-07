package com.jianghu.web.action.basic;

import java.io.IOException;
import java.util.List;

import com.common.Tools;
import com.jianghu.dao.Database;
import com.jianghu.domain.basic.Sequence;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 序列管理action
 * 
 * @author wangjinlong
 * 
 * @creatTime 2017年7月2日 上午10:08:42
 */
public class SequenceAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	public String getAllSeq() throws IOException {
		List<Sequence> list = Database.executeQuery("select * from bc_sequence", Sequence.class);
		Tools.serializerObjectInclude(list, new String[] { "*.seq_nam", "*.curr_val" });
		return null;
	}
}
