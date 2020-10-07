package com.cases.algorithm.greedy.backpack;

/**
 * 物体实体类
 * 
 * @creatTime 2018年9月17日 下午9:12:21
 * @author jinlong
 */
public class Goods implements Comparable<Goods> {
	private int id;// 编号
	private float weight;// 重量
	private float value;// 价值
	private float valOfWeight;// 价值/重量

	public Goods(int id, float weight, float value) {
		super();
		this.id = id;
		this.weight = weight;
		this.value = value;
		this.valOfWeight = value / weight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public float getValOfWeight() {
		return valOfWeight;
	}

	public void setValOfWeight(float valOfWeight) {
		this.valOfWeight = valOfWeight;
	}

	@Override
	public int compareTo(Goods goods) {
		// float a=10.222222225，b=10.222222229 ,用 == 为true
		if (Math.abs(this.valOfWeight - goods.valOfWeight) == 0) {
			return 0;
		} else if (this.valOfWeight < goods.valOfWeight) { // 降序
			return 1;
		} else {
			return -1;
		}
	}
}
