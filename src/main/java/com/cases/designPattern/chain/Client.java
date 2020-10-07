package com.cases.designPattern.chain;

import java.util.ArrayList;
import java.util.Random;

import com.cases.designPattern.chain.impl.Father;
import com.cases.designPattern.chain.impl.Husband;
import com.cases.designPattern.chain.impl.Son;
import com.cases.designPattern.chain.impl.Women;

public class Client {
	public static void main(String[] args) {
		Random random = new Random();
		ArrayList<IWomen> list = new ArrayList<IWomen>();
		for (int i = 0; i < 5; i++) {
			list.add(new Women(random.nextInt(4), "我要去逛街"));
		}

		Handler father = new Father();
		Handler husband = new Husband();
		Handler son = new Son();
		father.setNextHander(husband);
		husband.setNextHander(son);

		for (IWomen iWomen : list) {
			father.HandleMessage(iWomen);
		}
	}
}
