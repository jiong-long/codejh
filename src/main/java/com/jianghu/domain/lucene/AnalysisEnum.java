package com.jianghu.domain.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 常用分词器
 * @author wangjinlong
 *
 * @creatTime 2017年9月28日 下午3:55:16
 */
public final class AnalysisEnum{
	public static final Analyzer IKANALYZER;
	public static final Analyzer STANDARDANALYZER;
	public static final Analyzer CJKANALYZER;
	
	static{
		//存在一定的语义在里面，分词可能无法将一些关键词分出。如儿童床，无法分出床,需要配置自定义词库
		//升级jar包后可以自定义词典，但是较为麻烦，简单的方式是将IKAnalyzer Jar包中的dic文件中的*.dic文件复制出来添加
		IKANALYZER = new IKAnalyzer();
		//一元分词，一般不被使用
		STANDARDANALYZER = new StandardAnalyzer(Version.LUCENE_35);
		//二元分词
		CJKANALYZER = new CJKAnalyzer(Version.LUCENE_35);
	}
}
