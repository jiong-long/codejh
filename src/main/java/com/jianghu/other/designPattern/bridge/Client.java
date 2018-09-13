package com.jianghu.other.designPattern.bridge;

import com.jianghu.other.designPattern.bridge.impl.Clothes;
import com.jianghu.other.designPattern.bridge.impl.House;
import com.jianghu.other.designPattern.bridge.impl.HouseCorp;
import com.jianghu.other.designPattern.bridge.impl.ShanZhaiCorp;

public class Client {
	public static void main(String[] args) {
		House house = new House();
		HouseCorp houseCorp = new HouseCorp(house);
		houseCorp.makeMoney();

		ShanZhaiCorp shanZhaiCorp = new ShanZhaiCorp(new Clothes());
		shanZhaiCorp.makeMoney();
	}
}
