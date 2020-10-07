package com.cases.designPattern.factory.demo.impl;

import com.cases.designPattern.factory.demo.AbstractHumanFactory;
import com.cases.designPattern.factory.demo.Human;

/**
 * 生成人的工厂
 * 
 * @author jinlong
 *
 */
public class HumanFactory implements AbstractHumanFactory {
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Human> T createHuman(Class<T> c) {
		Human human = null;
		try {
			human = (T) Class.forName(c.getName()).newInstance();
		} catch (Exception e) {
			System.out.println("人种生成错误！");
		}
		return (T) human;
	}
}
