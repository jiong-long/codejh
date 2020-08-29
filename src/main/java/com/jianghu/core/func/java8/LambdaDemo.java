package com.jianghu.core.func.java8;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.IntStream;

import avro.shaded.com.google.common.collect.Lists;

public class LambdaDemo {
	public static void main(String args[]) {
		Person p1 = new Person().setName("张三").setSex("男").setAge(30);
		Person p2 = new Person().setName("李四").setSex("女").setAge(40);
		Person p3 = new Person().setName("王五").setSex("男").setAge(50);
		
		List<Person> list = Lists.newArrayList();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		//list.forEach(person -> System.out.println(person.toString()));
		
		//list.stream().filter(e -> e.getAge() > 40).forEach(person -> System.out.println(person.toString()));
		
		
		//List<Integer> ages = Arrays.asList(2,5,3,4,7);
        //IntSummaryStatistics statistics = ages.stream().mapToInt(e -> e).summaryStatistics();
		
		IntStream intStream =  IntStream.of(2,5,3,4,7);
		IntSummaryStatistics statistics = intStream.summaryStatistics();
        
		System.out.println("最大值: " + statistics.getMax());
        System.out.println("最小值: " + statistics.getMin());
        System.out.println("平均值: " + statistics.getAverage());
        System.out.println("总和: " + statistics.getSum());
        System.out.println("个数: " + statistics.getCount());

	}
	
	public void demo2() {
		//匿名内部类写法
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("内部类写法");
            }
        }).start();
        
        new Thread(() -> System.out.println("lambda写法")).start();  
	}
	
	public void demo1() {
		LambdaDemo tester = new LambdaDemo();

		// 类型声明
		MathOperation addition = (int a, int b) -> a + b;

		// 不用类型声明
		MathOperation subtraction = (a, b) -> a - b;

		// 大括号中的返回语句
		MathOperation multiplication = (int a, int b) -> {
			return a * b;
		};

		// 没有大括号及返回语句
		MathOperation division = (int a, int b) -> a / b;

		System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
		System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
		System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
		System.out.println("10 / 5 = " + tester.operate(10, 5, division));

		// 不用括号
		GreetingService greetService1 = message -> System.out.println("Hello " + message);
		// 用括号
		GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

		greetService1.sayMessage("Runoob");
		greetService2.sayMessage("Google");
	}

	@FunctionalInterface
	interface MathOperation {
		int operation(int a, int b);
	}

	@FunctionalInterface
	interface GreetingService {
		void sayMessage(String message);
	}

	private int operate(int a, int b, MathOperation mathOperation) {
		return mathOperation.operation(a, b);
	}
}

