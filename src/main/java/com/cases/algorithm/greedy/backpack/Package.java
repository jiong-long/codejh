package com.cases.algorithm.greedy.backpack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 贪婪法,解决0/1背包问题
 * 
 * @creatTime 2018年9月16日 下午11:00:47
 * @author jinlong
 */
public class Package {
	public static void main(String[] args) {
		//背包能承受的最大重量
		float biggestWeight = 150;
		Goods goods1 = new Goods(1, 35, 10);
		Goods goods2 = new Goods(2, 30, 40);
		Goods goods3 = new Goods(3, 60, 30);
		Goods goods4 = new Goods(4, 50, 50);
		Goods goods5 = new Goods(5, 40, 35);
		Goods goods6 = new Goods(6, 10, 40);
		Goods goods7 = new Goods(7, 25, 30);
		List<Goods> list = new ArrayList<Goods>();
		list.add(goods1);
		list.add(goods2);
		list.add(goods3);
		list.add(goods4);
		list.add(goods5);
		list.add(goods6);
		list.add(goods7);
		//将对象排序
		Collections.sort(list);

		float maxWeight = 0;
		float maxValue = 0;
		for (Goods goods : list) {
			if (maxWeight + goods.getWeight() <= biggestWeight) {
				maxWeight += goods.getWeight();
				maxValue += goods.getValue();
				System.out.println("取出" + goods.getId() + "号物体。");
			}
		}
		System.out.println("总重量：" + maxWeight);
		System.out.println("总价值：" + maxValue);
	}
}
