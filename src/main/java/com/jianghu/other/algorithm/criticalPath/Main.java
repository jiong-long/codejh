package com.jianghu.other.algorithm.criticalPath;

import java.util.Stack;

/**
 * 关键路径
 * 
 * @author wangjinlong
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {
		//v1  
		ArcNode a1 = new ArcNode(2, 3, 1, null);
		ArcNode a2 = new ArcNode(3, 2, 2, a1);

		//v2  
		ArcNode a3 = new ArcNode(4, 2, 3, null);
		ArcNode a4 = new ArcNode(5, 3, 4, a3);

		//v3  
		ArcNode a5 = new ArcNode(4, 4, 5, null);
		ArcNode a6 = new ArcNode(6, 3, 6, a5);

		//v4  
		ArcNode a7 = new ArcNode(6, 2, 7, null);

		//v5  
		ArcNode a8 = new ArcNode(6, 1, 8, null);

		int arcLength = 8;

		//定义一个事件（节点）
		HeadNode v1 = new HeadNode("v1", a2);
		HeadNode v2 = new HeadNode("v2", a4);
		HeadNode v3 = new HeadNode("v3", a6);
		HeadNode v4 = new HeadNode("v4", a7);
		HeadNode v5 = new HeadNode("v5", a8);
		HeadNode v6 = new HeadNode("v6", null);

		HeadNode[] hns = new HeadNode[] { v1, v2, v3, v4, v5, v6 };

		//step1:计算所有事件（节点）的入度
		int[] inDegree = AdjacencyList.getInDegree(hns);
		for (int i = 0; i < inDegree.length; i++) {
			//inDegree [0, 1, 1, 2, 1, 3]
			System.out.println("v" + (i + 1) + "的入度为：" + inDegree[i]);
		}

		System.out.println("************************************");

		//关键点：最晚开始时间的遍历顺序与最早开始时间的遍历顺序相反
		Stack<Integer> importStack = new Stack<Integer>();

		//step2:计算ve（事件（节点）的最早开始时间）
		int[] ve = AdjacencyList.toplogicalOrder(hns, inDegree, importStack);
		for (int i = 0; i < ve.length; i++) {
			System.out.println("v" + (i + 1) + "的最早开始时间为：" + ve[i]);
		}

		System.out.println("************************************");

		//step3: 计算vl（事件（节点）的最晚开始时间）
		int[] vl = AdjacencyList.lastHappen(hns, ve, importStack);
		for (int i = 0; i < vl.length; i++) {
			System.out.println("v" + (i + 1) + "的最晚开始时间为：" + vl[i]);
		}

		System.out.println("************************************");

		//step4:计算e（活动（边）的最早开始时间）
		int[] e = AdjacencyList.activityEarly(hns, ve, arcLength);
		for (int i = 0; i < e.length; i++) {
			System.out.println("a" + (i + 1) + "的最早开始时间为：" + e[i]);
		}

		System.out.println("************************************");

		//step5:计算 l（活动（边）的最晚开始时间）
		int[] l = AdjacencyList.activityLast(hns, vl, arcLength);
		for (int i = 0; i < l.length; i++) {
			System.out.println("a" + (i + 1) + "的最早开始时间为：" + l[i]);
		}

		System.out.println("************************************");

		//step6:计算关键路径
		Stack<Integer> key = AdjacencyList.keyWay(e, l);
		System.out.println("关键路径:" + key);

		Stack<Integer> keyNode = AdjacencyList.keyWay(ve, vl);
		System.out.println("关键路径上节点:" + keyNode);
	}
}
