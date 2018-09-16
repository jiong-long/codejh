package com.jianghu.other.algorithm.greedy;

/**
 * 贪婪法
 * 
 * @creatTime 2018年9月16日 下午11:00:47
 * @author jinlong
 */
public class Greedy {
	public static void main(String[] args) {
		//背包能承受的最大重量
		int biggestWeight = 150;
		//每个物品的重量
		float[] strWeight = { 35, 30, 60, 50, 40, 10, 25 };
		//每个物品对应的价值
		float[] strValue = { 10, 40, 30, 50, 35, 40, 30 };

		minWeight(biggestWeight, strWeight, strValue);
		maxValue(biggestWeight, strWeight, strValue);
		maxValueOFWeight(biggestWeight, strWeight, strValue);
	}

	/**
	 * 价值密度最大
	 * 
	 * @param biggestWeight
	 * @param strWeight
	 * @param strValue
	 */
	public static void maxValueOFWeight(int biggestWeight, float[] strWeight, float[] strValue) {
		float[] valueOfWeight = new float[strWeight.length];
		for (int i = 0; i < valueOfWeight.length; i++) {
			valueOfWeight[i] = strValue[i] / strWeight[i];
		}

		//将价值升序排列,对应的重量随之变化
		sort(valueOfWeight, strWeight, strValue);

		int weightCount = 0;//总重量
		int valueCount = 0;//总价值
		for (int i = strValue.length - 1; i >= 0; i--) {
			if (weightCount + strWeight[i] <= biggestWeight) {
				weightCount += strWeight[i];
				valueCount += strValue[i];
			}
		}
		System.out.println("最大价值密度");
		System.out.println("总重量:" + weightCount);
		System.out.println("总价值:" + valueCount);
	}

	/**
	 * 最大价值
	 * 
	 * @param biggestWeight
	 * @param strWeight
	 * @param strValue
	 */
	public static void maxValue(int biggestWeight, float[] strWeight, float[] strValue) {
		//将价值升序排列,对应的重量随之变化
		sort(strValue, strWeight);

		int weightCount = 0;//总重量
		int valueCount = 0;//总价值
		for (int i = strValue.length - 1; i >= 0; i--) {
			if (weightCount + strWeight[i] <= biggestWeight) {
				weightCount += strWeight[i];
				valueCount += strValue[i];
			}
		}
		System.out.println("最大价值");
		System.out.println("总重量:" + weightCount);
		System.out.println("总价值:" + valueCount);
	}

	/**
	 * 最小重量
	 * 
	 * @param biggestWeight
	 * @param strWeight
	 * @param strValue
	 */
	public static void minWeight(int biggestWeight, float[] strWeight, float[] strValue) {
		//将重量升序排列,对应的价值随之变化
		sort(strWeight, strValue);

		int weightCount = 0;//总重量
		int valueCount = 0;//总价值
		for (int i = 0; i < strWeight.length; i++) {
			if (weightCount + strWeight[i] <= biggestWeight) {
				weightCount += strWeight[i];
				valueCount += strValue[i];
			}
		}
		System.out.println("最小重量");
		System.out.println("总重量:" + weightCount);
		System.out.println("总价值:" + valueCount);
	}

	/**
	 * 排序（升序）
	 * 
	 * @param arr
	 *            排序数组
	 * @param arr2
	 *            变化数组
	 */
	private static void sort(float[] arr, float[] arr2) {
		//第一个代表循环多少次，一共循环length-1次
		for (int i = 0; i < arr.length - 1; i++) {
			//每次从第0个元素开始循环，第一次循环后，最大值在最后，所以循环次数减1
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					arr = swap(arr, j, j + 1);
					arr2 = swap(arr2, j, j + 1);
				}
			}
		}
	}

	private static void sort(float[] arr, float[] arr2, float[] arr3) {
		//第一个代表循环多少次，一共循环length-1次
		for (int i = 0; i < arr.length - 1; i++) {
			//每次从第0个元素开始循环，第一次循环后，最大值在最后，所以循环次数减1
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					arr = swap(arr, j, j + 1);
					arr2 = swap(arr2, j, j + 1);
					arr3 = swap(arr3, j, j + 1);
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
	public static float[] swap(float[] arr, int i, int j) {
		float temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return arr;
	}
}
