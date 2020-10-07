package com.common;

import org.springframework.util.StopWatch;

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
		StopWatch stopWatch = new StopWatch("Lucene更新");
		stopWatch.start();
		
		ItemAction.updateLucenePublic();
		
		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());
	}
}
