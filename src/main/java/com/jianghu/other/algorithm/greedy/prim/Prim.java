package com.jianghu.other.algorithm.greedy.prim;

import java.util.ArrayList;
import java.util.List;

public class Prim {
	static int MAX_VALUE = Integer.MAX_VALUE;

	// 定义地图变量 [节点编号][改节点到其他节点的权重]
	private int[][] map = new int[9][9];

	// 初始化地图
	public void initMap() {
		this.map[0] = new int[] { 0, 10, MAX_VALUE, MAX_VALUE, MAX_VALUE, 11, MAX_VALUE, MAX_VALUE, MAX_VALUE };
		this.map[1] = new int[] { 10, 0, 18, MAX_VALUE, MAX_VALUE, MAX_VALUE, 16, MAX_VALUE, 12 };
		this.map[2] = new int[] { MAX_VALUE, MAX_VALUE, 0, 22, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, 8 };
		this.map[3] = new int[] { MAX_VALUE, MAX_VALUE, 22, 0, 20, MAX_VALUE, MAX_VALUE, 16, 21 };
		this.map[4] = new int[] { MAX_VALUE, MAX_VALUE, MAX_VALUE, 20, 0, 26, MAX_VALUE, 7, MAX_VALUE };
		this.map[5] = new int[] { 11, MAX_VALUE, MAX_VALUE, MAX_VALUE, 26, 0, 17, MAX_VALUE, MAX_VALUE };
		this.map[6] = new int[] { MAX_VALUE, 16, MAX_VALUE, MAX_VALUE, MAX_VALUE, 17, 0, 19, MAX_VALUE };
		this.map[7] = new int[] { MAX_VALUE, MAX_VALUE, MAX_VALUE, 16, 7, MAX_VALUE, 19, 0, MAX_VALUE };
		this.map[8] = new int[] { MAX_VALUE, 12, 8, 21, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, 0 };
	}

	public Prim() {
		initMap();
	}

	public void prim() {
		int n = map.length;
		String[] c = new String[] { "v0", "v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8" };
		int[] lowcost = new int[n]; // 到新集合的最小权
		int[] mid = new int[n];// 存取前驱结点
		List<String> list = new ArrayList<String>();// 用来存储加入结点的顺序
		int i, j, min, minid, sum = 0;
		// 初始化辅助数组
		for (i = 1; i < n; i++) {
			lowcost[i] = map[0][i];
			mid[i] = 0;
		}
		list.add(c[0]);
		// 一共需要加入n-1个点
		for (i = 1; i < n; i++) {
			min = MAX_VALUE;
			minid = 0;
			// 每次找到距离集合最近的点
			for (j = 1; j < n; j++) {
				if (lowcost[j] != 0 && lowcost[j] < min) {
					min = lowcost[j];
					minid = j;
				}
			}

			if (minid == 0)
				return;
			list.add(c[minid]);
			lowcost[minid] = 0;
			sum += min;
			System.out.println(" 第  " + i + " 条边为 : ( " + c[mid[minid]] + " , " + c[minid] + " ) = " + min + " ");
			// 加入该点后，更新其它点到集合的距离
			for (j = 1; j < n; j++) {
				if (lowcost[j] != 0 && lowcost[j] > map[minid][j]) {
					lowcost[j] = map[minid][j];
					mid[j] = minid;
				}
			}
		}
		System.out.println("sum:" + sum);
	}

	public static void main(String[] args) {
		Prim prim = new Prim();
		prim.prim();
	}
}
