package com.jianghu.other.algorithm.sort;

import java.util.Arrays;

/**
 * 基于堆的排序（最小堆）(降序排列)
 * 
 * @author jinlong
 *
 */
public class Head {
	public static void main(String[] args) {
		int[] arr = { 23, 1, 45, 12, 24, 11, 66, 55, 4 };
		//构造出最小堆
		arr = make_head1(arr);

		//降序排列
		for (int i = arr.length - 1; i >= 0; i--) {
			//将最小的和最后一个互换
			swap(arr, i, 0);
			//重新构造出最小堆
			sift_down(arr, i - 1, 0);
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\t");
		}
	}

	/**
	 * 构造堆（通过最后一个非叶子节点不断向下比较交换）
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] make_head(int[] arr) {
		int n = arr.length;
		for (int i = n / 2; i > 0; i--) {
			sift_down(arr, arr.length, i - 1);
		}
		return arr;
	}

	/**
	 * 构造堆，通过不断在最后插入元素实现
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] make_head1(int[] arr) {
		int[] temp = new int[0];
		for (int i : arr) {
			temp = insert(temp, i);
		}
		return temp;
	}

	/**
	 * 元素删除(与最大堆，最小堆无关)
	 * 
	 * @param arr
	 * @param i
	 *            待删除元素的下标
	 */
	public void delete(int[] arr, int i) {
		int n = arr.length;
		int x = arr[i];//待删除的元素
		int y = arr[n];//最后一个元素
		if (i <= n) {
			arr[i] = y;//最后一个元素赋值给待删除的元素
			arr = Arrays.copyOf(arr, n - 1);
			if (x > y) {//最大堆
				sift_down(arr, arr.length, i);
			} else {//最小堆
				sift_up(arr, i);
			}
		}
	}

	/**
	 * 元素插入(与最大堆，最小堆无关)
	 * 
	 * @param arr
	 * @param x
	 *            待插入的元素
	 */
	public static int[] insert(int[] arr, int x) {
		int n = arr.length;
		//增加数组的长度
		arr = Arrays.copyOf(arr, n + 1);
		//将新元素增加到数组末尾
		arr[n] = x;
		//通过元素上移来确定该元素的位置
		sift_up(arr, n);
		return arr;
	}

	/**
	 * 元素下移操作(最小堆)
	 * 
	 * 元素和两个子节点中较小的交换
	 * 
	 * @param arr
	 * @param i
	 *            元素的下标
	 */
	public static void sift_down(int[] arr, int n, int i) {
		boolean done = true;
		if (2 * i + 1 <= n) {//下移的节点最大为最后一个节点
			while (done && (i = (2 * i + 1)) <= n) {//i变为左子节点
				if (i + 1 <= n && arr[i] > arr[i + 1]) {//右子节点没有越界，并且右子节点小
					i++;
				}
				if (arr[(i - 1) / 2] > arr[i]) {
					swap(arr, (i - 1) / 2, i);
				} else {
					done = false;
				}
			}
		}
	}

	/**
	 * 元素上移操作(最小堆)
	 * 
	 * 直接和父节点比较，比父节点小就交换
	 * 
	 * @param arr
	 * @param i
	 *            元素的下标
	 */
	public static void sift_up(int[] arr, int i) {
		boolean done = true;
		if (i > 0) {//不是根节点
			while (done && i > 0) {
				if (arr[i] < arr[(i - 1) / 2]) {//小于父节点
					swap(arr, i, (i - 1) / 2);
				} else {
					done = false;
				}
				//小于父节点还有可能小于爷爷节点，不断向上遍历
				i = (i - 1) / 2;
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
