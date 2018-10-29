package com.jianghu.other.RabbitMQ;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * MQ相关工具类
 * 
 * @creatTime 2018年10月29日 下午10:19:51
 * @author jinlong
 */
public class Tools {

	/**
	 * 获取Connection
	 * 
	 * @return
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public static Connection getConnection() throws IOException, TimeoutException {
		//创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		//设置RabbitMQ相关信息
		factory.setHost("localhost");
		//factory.setUsername("");
		//factory.setPassword("");
		//factory.setPort(2088);
		//创建一个新的连接
		return factory.newConnection();
	}
}
