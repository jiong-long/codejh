package com.jianghu.other.algorithm.random.Sherwood;

import java.util.Random;

/**
 * 舍伍德算法解决排序问题
 * 
 * @creatTime 2018年11月1日 下午11:54:14
 * @author jinlong
 */
public class Sort {
	public static void main(String[] args) {
		Random random = new Random();
		int[] a = { 23, 1, 45, 12, 24, 11, 66, 55, 4 };
		quickSort(a, 0, a.length - 1, random);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + "\t");
		}
	}

	public static void quickSort(int[] a, int left, int right, Random random) {
		if (left < right) {//递归出口条件
			if (left < right) {//递归出口条件
				int i = left;
				int j = right;
				//随机化选取标杆
				int ran = left + random.nextInt(right - left);
				//将该标杆移动到首位
				swap(a, left, ran);
				//记录该标杆
				int x = a[left];
				while (i < j) {
					//从右向左找第一个小于x的数的下标
					while (i < j && a[j] >= x) {
						j--;
					}
					//将改数字放在i（头）的位置上
					if (i < j) {
						a[i++] = a[j];
					}
					//从左向右找第一个大于等于x的数的下标
					while (i < j && a[i] < x) {
						i++;
					}
					//将改数字放在j（尾）的位置上
					if (i < j) {
						a[j--] = a[i];
					}
				}
				//插入标尺，此时左边都比标尺小，右边都比标尺大
				a[i] = x;
				//递归左边
				quickSort(a, left, i - 1, random);
				//递归右边
				quickSort(a, i + 1, right, random);
			}
		}
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
