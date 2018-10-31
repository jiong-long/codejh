package com.jianghu.other.RabbitMQ.routing;

import com.jianghu.other.RabbitMQ.RabbitTools;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 路由模式
 * 
 * @author lenovo
 *
 */
public class Producer {

	private final static String EXCHANGE_NAME = "test_exchange_direct";

	public static void main(String[] argv) throws Exception {
		// 获取到连接以及mq通道
		Connection connection = RabbitTools.getConnection();
		Channel channel = connection.createChannel();

		// 声明exchange(direct:将消息发送到绑定了该EXCHANGE_NAME routingKey为"A"的队列中)
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");

		// 消息内容
		String message = "这是消息A";
		channel.basicPublish(EXCHANGE_NAME, "A", null, message.getBytes());
		System.out.println(" [生产者] Sent '" + message + "'");

		channel.close();
		connection.close();
	}
}
