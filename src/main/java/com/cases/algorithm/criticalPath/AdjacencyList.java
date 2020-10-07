package com.cases.algorithm.criticalPath;

import java.util.Stack;

public class AdjacencyList {
	/**
	 * 计算关键路径
	 * 
	 * @param e
	 * @param l
	 * @return
	 */
	public static Stack<Integer> keyWay(int[] e, int[] l) {
		Stack<Integer> key = new Stack<Integer>();
		for (int i = 0; i < e.length; i++) {
			if (e[i] == l[i]) {
				key.push(i + 1);
			}
		}
		return key;
	}

	/**
	 * 计算活动（边）的最晚开始时间
	 * 
	 * @param hns
	 * @param vl
	 * @param length
	 * @return
	 */
	public static int[] activityLast(HeadNode[] hns, int[] vl, int length) {
		int[] l = new int[length];
		for (int i = 0; i < l.length; i++) {
			l[i] = 0;
		}

		//活动的最晚开始时间为：下一节点的昨晚开始时间 - 活动的长度
		for (int i = 0; i < hns.length; i++) {
			for (ArcNode n = hns[i].firstArc; n != null; n = n.nextArc) {
				int k = n.adjvex - 1;
				int j = n.edge;
				l[j - 1] = vl[k] - n.data;
			}
		}
		return l;
	}

	/**
	 * 计算活动（边的最早开始时间）
	 * 
	 * @param hns
	 * @param ve
	 * @param length
	 */
	public static int[] activityEarly(HeadNode[] hns, int[] ve, int length) {
		int[] e = new int[length];
		for (int i = 0; i < e.length; i++) {
			e[i] = 0;
		}

		//活动（边）的最早开始时间为：该活动上一节点的最早开始时间
		for (int i = 0; i < hns.length; i++) {
			for (ArcNode n = hns[i].firstArc; n != null; n = n.nextArc) {
				int j = n.edge;
				e[j - 1] = ve[i];
			}
		}
		return e;
	}

	/**
	 * 计算事件（节点）的最晚开始时间
	 * 
	 * @param hns
	 * @param ve
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public static int[] lastHappen(HeadNode[] hns, int[] ve, Stack<Integer> importStack) throws Exception {
		int[] vl = new int[hns.length];
		for (int i = 0; i < vl.length; i++) {
			vl[i] = ve[ve.length - 1];//初始化设置所有事件（节点）的最晚开始时间为关键路径的长度
		}

		int i = importStack.pop();//最后一个节点无需计算
		while (!importStack.isEmpty()) {
			i = importStack.pop();
			for (ArcNode n = hns[i].firstArc; n != null; n = n.nextArc) {
				int j = n.adjvex - 1;
				if (vl[i] > vl[j] - n.data) {
					vl[i] = vl[j] - n.data;
				}
			}
		}
		return vl;
	}

	/**
	 * 获得每个节点的入度。 why:节点的入度就是该节点需要遍历的次数
	 * 
	 * @param hns
	 * @return
	 */
	public static int[] getInDegree(HeadNode[] hns) {
		int[] inDegree = new int[hns.length];
		for (int i = 0; i < inDegree.length; i++) {
			inDegree[i] = 0;//初始化设置所有事件（节点）的入度为0
		}
		//求每个节点的入度 ，有多少边指向这个节点  
		for (int i = 0; i < hns.length; i++) {
			for (ArcNode n = hns[i].firstArc; n != null; n = n.nextArc) {
				inDegree[n.adjvex - 1]++;
			}
		}
		return inDegree;
	}

	/**
	 * 计算事件（节点的最早开始时间）
	 * 
	 * @param hns
	 * @param inDegree
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public static int[] toplogicalOrder(HeadNode[] hns, int[] inDegree, Stack<Integer> importStack) throws Exception {
		//所有的源点（开始节点）
		Stack<Integer> start = new Stack<Integer>();
		//入度为0的节点（开始节点）保存在栈中
		for (int i = 0; i < inDegree.length; i++) {
			if (inDegree[i] == 0) {
				start.push(i);//将元素压入栈中
			}
		}

		int[] ve = new int[hns.length];
		for (int i = 0; i < ve.length; i++) {
			ve[i] = 0;//初始化设置每个节点的最早开始时间为0
		}

		int count = 0;

		//isEmpty()是从Vector继承的方法    empty()方法是Stack自己实现的方法
		while (!start.isEmpty()) {
			int i = start.pop();//当前元素出栈  
			importStack.push(i);//遍历顺序（下标）：[0, 1, 4, 2, 3, 5]
			count++;
			for (ArcNode n = hns[i].firstArc; n != null; n = n.nextArc) {
				int j = n.adjvex - 1; //事件（节点）下标  = 事件（节点）ID - 1
				inDegree[j]--; // 遍历一次，事件（节点）的入度减1
				if (inDegree[j] == 0) {//入度为0，则该节点遍历完成
					start.push(j);
				}
				if (ve[i] + n.data > ve[j]) {
					ve[j] = ve[i] + n.data;
				}
			}
		}
		//ve [0, 3, 2, 6, 6, 8]
		if (count < hns.length) {
			throw new Exception("有环！");
		}
		return ve;
	}
}
