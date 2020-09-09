package com.jianghu.core.func.java8;

import java.util.List;
import java.util.stream.Collectors;

import avro.shaded.com.google.common.collect.Lists;

public class StreamDemo {
	public static void main(String[] args) {
		Person p1 = new Person().setName("张三").setSex("男").setAge(30);
		Person p3 = new Person().setName("王五").setSex("男").setAge(50);
		Person p2 = new Person().setName("李四").setSex("女").setAge(40);
		Person p4 = new Person().setName("李四1").setSex("女").setAge(50);
		Person p5 = new Person().setName("李四2").setSex("女").setAge(20);
		
		List<Person> list = Lists.newArrayList();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		
		list.stream()
			.map(Person::doubleAge)
			.distinct()
			.sorted()
			.collect(Collectors.toList())
			.forEach(System.out::println);
	}
}
