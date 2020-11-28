package com.cases.mq.RabbitMQ.Work;

import com.cases.mq.RabbitMQ.RabbitTools;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 生产者(多劳多得，一个消费者获得，其他消费者就获取不到)
 * 
 * @creatTime 2018年10月30日 下午9:47:58
 * @author jinlong
 */
public class Producer {

	private final static String QUEUE_NAME = "test_queue_work";

	public static void main(String[] argv) throws Exception {
		// 获取到连接以及mq通道
		Connection connection = RabbitTools.getConnection();
		Channel channel = connection.createChannel();

		// 声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		for (int i = 0; i < 50; i++) {
			// 消息内容
			String message = "" + i;
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println(" [生产者] Sent '" + message + "'");
			//发送的消息间隔越来越长
			Thread.sleep(i * 10);
		}

		channel.close();
		connection.close();
	}
}
