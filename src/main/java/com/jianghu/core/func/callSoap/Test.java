package com.jianghu.core.func.callSoap;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

/**
 * 测试类
 * @author wangjinlong
 *
 */
public class Test {
	public static void main(String[] args) throws RemoteException, ServiceException {
		CustomerImplServiceLocator createServiceLocator = new CustomerImplServiceLocator();
		CustomerImplServiceSoapBindingStub service = (CustomerImplServiceSoapBindingStub) createServiceLocator.getCustomerImplPort();
		
		//用户信息
		Contacts customerUser1 = new Contacts("张三", "18061661234");
		Contacts customerUser2 = new Contacts("李四", "178236874321");
		Contacts[] customerUser1Arr = new Contacts[]{customerUser1, customerUser2};
		Contacts[][] userArr = new Contacts[][]{customerUser1Arr, customerUser1Arr};
		//客户信息
		Customer customer = new Customer("01", "11", "22", userArr, "44", "55", "66", "77", "88", "99", "00", "111", "222", "1001", "01");
		//调用接口，返回对象
		ReturnObj returnObj = service.insertCustomer(customer);
		
		System.out.println(returnObj.getZremaek());
		System.out.println(returnObj.getZzba());
	}
}
