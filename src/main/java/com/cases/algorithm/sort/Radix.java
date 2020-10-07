package com.cases.algorithm.sort;

/**
 * 基数排序
 * 
 * @author jinlong
 *
 */
public class Radix {
	public static void main(String[] args) {
		int[] arr = { 23, 1, 45, 12, 24, 11, 66, 55, 11 };
		radixSort(arr);
		for (int i : arr) {
			System.out.println(i);
		}
	}

	private static void radixSort(int[] array) {
		//算出最长的元素,即最大的循环次数
		int maxLength = 0;
		for (int num : array) {
			maxLength = Math.max(maxLength, (num + "").length());
		}

		int n = 0;//当前是第几位,0代表最后一位
		int length = array.length;
		//排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
		int[][] bucket = new int[10][length];
		//用于保存每个桶里有多少个数字
		int[] order = new int[length];
		while (n < maxLength) {
			int k = 0;//遍历array数组的计数器
			//将数组array里的每个数字放在相应的桶里
			for (int num : array) {
				int digit = ((num / (int) (Math.pow(10, n))) % 10);
				bucket[digit][order[digit]] = num;
				order[digit]++;
			}
			//将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
			for (int i = 0; i < length; i++) {
				//这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
				if (order[i] != 0) {
					for (int j = 0; j < order[i]; j++) {
						array[k] = bucket[i][j];
						k++;
					}
				}
				//将桶里计数器置0，用于下一次位排序
				order[i] = 0;
			}
			n++;
		}
	}

}
