package com.jianghu.domain.lucene;

import java.util.ArrayList;
import java.util.List;

import com.jianghu.core.tools.LuceneUtil;

public class LuceneThread implements Runnable {
	private List<LuceneField> field_list = new ArrayList<LuceneField>();

	public LuceneThread(List<LuceneField> field_list) {
		this.field_list = field_list;
	}

	public void run() {
		LuceneUtil lucene = LuceneUtil.instance();
		try {
			System.out.println("当前线程名称为：" + Thread.currentThread().getName());
			lucene.deleteAllIndex();
			lucene.writeIndex(this.field_list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
