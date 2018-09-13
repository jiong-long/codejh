package com.jianghu.core.func;

import com.jianghu.web.action.basic.ItemAction;

/**
 * 定时器，目前只用作定期更新lucene
 * 
 * @creatTime 2017年10月2日 下午9:17:32
 * @author jinlong
 * 
 */
public class SpringQuartz {
	protected void execute() {
		long begin = System.currentTimeMillis();
		System.out.println("===============开始更新Lucene===============");
		ItemAction.updateLucenePublic();
		System.out.println("===============更新Lucene结束===============");
		long end = System.currentTimeMillis();
		System.out.println("===============更新时间:" + (end - begin) + "===============");
	}
}
