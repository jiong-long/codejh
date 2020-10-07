package com.cases.designPattern.visitor;

import java.util.ArrayList;
import java.util.List;

import com.cases.designPattern.visitor.impl.CommonEmployee;
import com.cases.designPattern.visitor.impl.Manager;
import com.cases.designPattern.visitor.impl.Visitor;

public class Client {
	public static void main(String[] args) {
		for (Employee emp : mockEmployee()) {
			emp.accept(new Visitor());
		}
	}

	public static List<Employee> mockEmployee() {
		List<Employee> list = new ArrayList<Employee>();
		CommonEmployee zhangsan = new CommonEmployee();
		//CommonEmployee zhangsan这个可以调用，Employee zhangsan不行
		zhangsan.setJob("程序员");
		zhangsan.setName("张三");
		zhangsan.setSalary("1800");
		zhangsan.setSex(Employee.MALE);
		list.add(zhangsan);

		Manager lisi = new Manager();
		lisi.setName("李四");
		lisi.setPerformance("业绩好");
		lisi.setSalary("18090");
		lisi.setSex(Employee.FEMALE);
		list.add(lisi);

		return list;
	}
}
