package com.cases.algorithm.greedy.Kruskal;

import java.util.ArrayList;
import java.util.List;

/**
 * 克鲁斯卡尔算法
 * 
 * @author wangjinlong
 * @createTime 2018年9月23日 下午12:02:34
 */
public class KruskalMiniCostSpanningTree {
	// 计数器
	private int counter = 0;

	private int[] parent = new int[9];

	// 边集合,按照权重升序排列
	private List<Edge> edgesList = new ArrayList<Edge>();

	// 定义节点
	private String nodes[] = { "vo", "v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8" };

	public KruskalMiniCostSpanningTree() {
		this.initEdages();
	}

	// 初始化边和权的list集合
	public void initEdages() {
		// 必须按照【权重升序】排列
		Edge v0 = new Edge(4, 7, 7);
		Edge v1 = new Edge(2, 8, 8);
		Edge v2 = new Edge(0, 1, 10);
		Edge v3 = new Edge(0, 5, 11);
		Edge v4 = new Edge(1, 8, 12);
		Edge v5 = new Edge(3, 7, 16);
		Edge v6 = new Edge(1, 6, 16);
		Edge v7 = new Edge(5, 6, 17);
		Edge v8 = new Edge(1, 2, 18);
		Edge v9 = new Edge(6, 7, 19);
		Edge v10 = new Edge(3, 4, 20);
		Edge v11 = new Edge(3, 8, 21);
		Edge v12 = new Edge(2, 3, 22);
		Edge v13 = new Edge(3, 6, 24);
		Edge v14 = new Edge(4, 5, 26);

		this.edgesList.add(v0);
		this.edgesList.add(v1);
		this.edgesList.add(v2);
		this.edgesList.add(v3);
		this.edgesList.add(v4);
		this.edgesList.add(v5);
		this.edgesList.add(v6);
		this.edgesList.add(v7);
		this.edgesList.add(v8);
		this.edgesList.add(v9);
		this.edgesList.add(v10);
		this.edgesList.add(v11);
		this.edgesList.add(v12);
		this.edgesList.add(v13);
		this.edgesList.add(v14);
	}

	// 克鲁斯卡尔算法
	public void kruskal() {
		int n = -1, m = -1, begin, end;
		for (int i = 0; i < this.edgesList.size(); i++) { // 遍历得到每一条边
			begin = this.edgesList.get(i).getBegin();// 开始节点
			end = this.edgesList.get(i).getEnd();// 结束节点
			n = this.find(begin);
			m = this.find(end);
			if (n != m) { // 说明没有闭合线路
				this.counter++;
				this.parent[n] = m;// 每个顶点都作为树的根节点，构成森林
				System.out.println(" 第  " + this.counter + " 条边为 : ( " + this.nodes[begin] + " , " + this.nodes[end]
						+ " ) = " + this.edgesList.get(i).getWeight() + " ");
			}
		}
	}

	public int find(int index) {
		while (this.parent[index] > 0) {
			index = this.parent[index];
		}
		return index;
	}

	public static void main(String[] args) {
		KruskalMiniCostSpanningTree kruskalMiniCostSpanningTree = new KruskalMiniCostSpanningTree();
		kruskalMiniCostSpanningTree.kruskal();
	}
}
