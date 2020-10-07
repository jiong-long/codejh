package com.cases.algorithm.sort;

/**
 * 合并排序（升序）
 * 
 * @author jinlong
 *
 */
public class Merge {
	public static void main(String[] args) {
		int[] arr = { 23, 1, 45, 12, 24, 11, 66, 55, 11 };
		arr = merge_sort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\t");
		}
	}

	public static int[] merge_sort(int[] arr) {
		int[] returnArr = new int[arr.length];
		int[] temp = null;
		int i, s, k, t = 1;
		while (t <= arr.length) {
			s = t;
			t = s * 2;
			i = 0;
			while (i + t <= arr.length) {
				temp = merge_arr(arr, i, i + s - 1, i + t - 1);
				k = 0;
				for (int j = i; j <= i + t - 1; j++) {
					returnArr[j] = temp[k++];
				}
				i = i + t;
			}

			if (i + s <= arr.length) {
				temp = merge_arr(arr, i, i + s - 1, arr.length - 1);
				k = 0;
				for (int j = i; j <= arr.length - 1; j++) {
					returnArr[j] = temp[k++];
				}
			}
			arr = returnArr;
		}
		return returnArr;
	}

	/**
	 * 合并两个有序数组
	 * 
	 * @param arr
	 * @param start
	 * @param middle
	 * @param end
	 */
	public static int[] merge_arr(int[] arr, int start, int middle, int end) {
		int[] returnArr = new int[end - start + 1];
		int k = 0;
		int i = start;
		int j = middle + 1;
		//逐一判断两个子数组的元素
		while (i <= middle && j <= end) {
			//按照两种情况将较小的元素放入缓存区中
			if (arr[i] < arr[j]) {
				returnArr[k++] = arr[i++];
			} else {
				returnArr[k++] = arr[j++];
			}
		}

		if (i == middle + 1) {//后面一个数组较长，将后面的剩余元素复制进去
			while (j <= end) {
				returnArr[k++] = arr[j++];
			}
		} else {
			while (i <= middle) {//前面一个数组较长，将前面的剩余元素复制进去
				returnArr[k++] = arr[i++];
			}
		}

		return returnArr;
	}
}
