package com.cases.algorithm.criticalPath;

/**
 * 边
 * 
 * @author wangjinlong
 *
 */
class ArcNode {
	int adjvex;//下一节点（HeadNode）唯一标识
	int data;//边的长度
	int edge;//边的唯一标识
	ArcNode nextArc;//指向下一个邻接顶点的指针(从同一个节点HeadNode发出的边)

	public ArcNode(int adjvex, int data, int edge, ArcNode nextArc) {
		this.adjvex = adjvex;
		this.data = data;
		this.nextArc = nextArc;
		this.edge = edge;
	}

	public int getAdjvex() {
		return adjvex;
	}

	public void setAdjvex(int adjvex) {
		this.adjvex = adjvex;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public ArcNode getNextArc() {
		return nextArc;
	}

	public void setNextArc(ArcNode nextArc) {
		this.nextArc = nextArc;
	}
}
