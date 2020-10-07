package com.cases.algorithm.criticalPath;

/**
 * 节点
 * 
 * @author wangjinlong
 *
 */
public class HeadNode {
	private String data;//节点的信息  
	ArcNode firstArc;//指向第一个邻接顶点的指针  

	public HeadNode() {

	}

	public HeadNode(String data) {
		this.data = data;
	}

	public HeadNode(String data, ArcNode firstArc) {
		this.data = data;
		this.firstArc = firstArc;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public ArcNode getFirstArc() {
		return firstArc;
	}

	public void setFirstArc(ArcNode firstArc) {
		this.firstArc = firstArc;
	}
}