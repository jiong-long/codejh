package com.cases.algorithm.greedy.Kruskal;

/**
 * 边实体
 * 
 * @author wangjinlong
 * @createTime 2018年9月23日 上午11:58:30
 */
public class Edge {
	private int begin;// 开始节点
	private int end;// 结束节点
	private int weight;// 权重

	public Edge(int begin, int end, int weight) {
		this.begin = begin;
		this.end = end;
		this.weight = weight;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
