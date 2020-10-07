package com.cases.designPattern.bridge;

import com.cases.designPattern.bridge.impl.Clothes;
import com.cases.designPattern.bridge.impl.House;
import com.cases.designPattern.bridge.impl.HouseCorp;
import com.cases.designPattern.bridge.impl.ShanZhaiCorp;

public class Client {
	public static void main(String[] args) {
		House house = new House();
		HouseCorp houseCorp = new HouseCorp(house);
		houseCorp.makeMoney();

		ShanZhaiCorp shanZhaiCorp = new ShanZhaiCorp(new Clothes());
		shanZhaiCorp.makeMoney();
	}
}
