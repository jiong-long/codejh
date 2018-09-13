package com.jianghu.other.algorithm.sort;

/**
 * 冒泡排序(升序)
 * 
 * @author wangjinlong
 *
 */
public class Bubble {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = { 23, 1, 45, 12, 24, 11, 66, 55, 4 };
		sort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\t");
		}
	}

	/**
	 * 排序算法
	 * 
	 * @param arr
	 */
	public static void sort(int[] arr) {
		//第一个代表循环多少次，一共循环length-1次
		for (int i = 0; i < arr.length - 1; i++) {
			//每次从第0个元素开始循环，第一次循环后，最大值在最后，所以循环次数减1
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					arr = swap(arr, j, j + 1);
				}
			}
		}
	}

	/**
	 * 交换两个元素的位置
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 * @return
	 */
	public static int[] swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return arr;
	}
}
