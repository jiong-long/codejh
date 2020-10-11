package com.cases.java8;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdaDemo {
	
	public static void main(String args[]) {
		//demo1();
		//functionDemo();
		//consumerDemo();
		supplierDemo();
	}
	
	public static void supplierDemo() {
        Supplier<Person> supplierPerson = Person::new;

        Person person = supplierPerson.get();
        person.setName("dd");
        System.out.println(person.getName());
	}
	
	public static void consumerDemo() {
		Consumer<Integer> consumer = x -> {
            int a = x + 2;
            System.out.println(a);
            System.out.println(a + "_");
        };
        consumer.accept(4);
	}

	public static void functionDemo() {
		Function<Integer, String> function2 = x -> x * 2 + "dd";
        System.out.println(function2.apply(4));

        Function<String, Person> objFunction2 = str -> new Person().setName(str);
        System.out.println(objFunction2.apply("dd").getName());
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
	
	/**
	 * 自定义接口
	 */
	public static void demo1() {
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

