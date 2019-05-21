package com.jianghu.web.webservice.soap;

import javax.jws.WebService;
import org.springframework.stereotype.Component;

/**
 * 客户实现
 * @author wangjinlong
 *
 */
@Component
@WebService(endpointInterface = "com.jianghu.web.webservice.soap.ICustomer")
public class CustomerImpl implements ICustomer {
	
	/**
	 * 插入客户数据
	 */
	public ReturnObj insertCustomer(Customer customer) {
		System.out.println(customer.getId());
		System.out.println(customer.getName());
		CustomerUser[][] userArr = customer.getUserArr();
		for (CustomerUser[] users : userArr) {
			for (CustomerUser user : users) {
				System.out.println(user.getUser_id());
				System.out.println(user.getUser_name());
			}
		}
		
		//返回对象
		ReturnObj obj = new ReturnObj();
		obj.setCode("success");
		obj.setMsg("成功");
		return obj;
	}
	
}
