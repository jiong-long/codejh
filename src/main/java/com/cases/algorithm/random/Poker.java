package com.cases.algorithm.random;

import java.util.Random;

/**
 * 洗牌算法
 * 
 * @creatTime 2018年11月1日 下午9:25:17
 * @author jinlong
 */
public class Poker {
	static int array[] = new int[52];

	public static void main(String[] args) {
		for (int i = 0; i < array.length; i++) {
			array[i] = i;
		}
		int a[] = new int[13];
		int b[] = new int[13];
		int c[] = new int[13];
		int d[] = new int[13];
		deal(a, b, c, d);
		//这样输出方便
		for (int i = 0; i < 13; i++) {
			System.out.println(a[i] + "\t" + b[i] + "\t" + c[i] + "\t" + d[i]);
		}

	}

	private static void deal(int[] a, int[] b, int[] c, int[] d) {
		Random random = new Random();
		for (int i = 0; i < 52; i++) {
			int j = random.nextInt(52);
			if (i != j) {
				swap(array, i, j);
			}
		}

		for (int i = 0; i < 13; i++) {
			a[i] = array[i];
			b[i] = array[i + 13];
			c[i] = array[i + 26];
			d[i] = array[i + 39];
		}
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}