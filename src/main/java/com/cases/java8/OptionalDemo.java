package com.cases.java8;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * @author wangjinlong
 */
@Slf4j
public class OptionalDemo {
	public static void main(String args[]) {
		orElseAndOrElseGet();
	}

	private static void orElseAndOrElseGet(){
		Person person1 = Person.builder().name("张三").build();
		log.info("Using orElse");
		Optional.ofNullable(person1).orElse(createPerson());
		log.info("Using orElseGet");
		//对象不为空时，不会调用orElseGet中的内容
		Optional.ofNullable(person1).orElseGet(() -> createPerson());

	}

	private static Person createPerson(){
		log.info("创建Person对象");
		return Person.builder().name("").build();
	}

	private static void sum() {
		OptionalDemo java8Tester = new OptionalDemo();
		Integer value1 = null;
		Integer value2 = new Integer(10);
		// Optional.ofNullable - 允许传递为 null 参数
		Optional<Integer> a = Optional.ofNullable(value1);
		// Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
		Optional<Integer> b = Optional.of(value2);

		// Optional.isPresent - 判断值是否存在
		System.out.println("第一个参数值存在: " + a.isPresent());
		System.out.println("第二个参数值存在: " + b.isPresent());
		
		// Optional.orElse - 如果值存在，返回它，否则返回默认值
		value1 = a.orElse(new Integer(0));

		// Optional.get - 获取值，值需要存在
		value2 = b.get();
		System.out.println(value1 + value2);
	}
}
