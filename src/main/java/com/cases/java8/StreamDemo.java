package com.cases.java8;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
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
		
		//转Map Function.identity()表示当前Person对象
		list.stream().collect(Collectors.toMap(Person::getName, Function.identity())).forEach((k, v) -> {
			System.out.println(k + "\t" + v.getAge());
		});
		
		//计算个数
        Long countList = list.stream().collect(Collectors.counting());
        System.out.println("Collectors.counting()：" + countList);

        //求和
        Integer sumAges = list.stream().collect(Collectors.summingInt(Person::getAge));
        System.out.println("Collectors.summingInt：" + sumAges);

        //平均数
        Double aveAges = list.stream().collect(Collectors.averagingInt(Person::getAge));
        System.out.println("Collectors.averagingInt：" + aveAges);


        IntSummaryStatistics intSummary = list.stream().collect(Collectors.summarizingInt(Person::getAge));
        System.out.println("SummaryStatistics操作集合：");
        System.out.println(intSummary.getAverage());
        System.out.println(intSummary.getMax());
        System.out.println(intSummary.getMin());
        System.out.println(intSummary.getSum());
        
        String strEmp2 = list.stream().map(person -> person.getName()).collect(Collectors.joining("-中间的分隔符-", "前缀*", "&后缀"));
        System.out.println("连接字符串：" + strEmp2);
        
        Optional<Integer> maxAge = list.stream().map(emp -> emp.getAge())
                .collect(Collectors.maxBy(Comparator.comparing(Function.identity())));
        System.out.println("返回Optional对象：" + maxAge.orElse(0));
		
		System.out.println("-------------------------------------");
		
		list.stream()
			.map(Person::doubleAge)
			.distinct()
			.sorted()
			.filter(age -> age > 40)
			.collect(Collectors.toList())
			.forEach(System.out::println);
		
		System.out.println("-------------------------------------");
		
		boolean flag1 = list.stream()
				.anyMatch(person -> person.getAge() > 20);
		System.out.println("anyMatch:" + flag1);
		
		boolean flag2 = list.stream()
				.allMatch(person -> person.getAge() > 20);
		System.out.println("allMatch:" + flag2);
		
		long count = list.stream()
				.filter(person -> person.getAge() > 30)
				.count();
		System.out.println("count:" + count);
		
		System.out.println("-------------------------------------");
		
		int result1 = list.stream().mapToInt(Person::getAge).reduce((a, b) -> a + b).getAsInt();
		System.out.println("reduce1:" + result1);
		int result2 = list.stream().mapToInt(Person::getAge).reduce(10, (a, b) -> a + b);
		System.out.println("reduce2:" + result2);
		
	}
}
