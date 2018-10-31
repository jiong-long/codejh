package com.jianghu.other.RabbitMQ.routing;

import com.jianghu.other.RabbitMQ.Tools;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class Consumer1 {

	private final static String QUEUE_NAME = "test_queue_direct_1";

	private final static String EXCHANGE_NAME = "test_exchange_direct";

	public static void main(String[] argv) throws Exception {
		// 获取到连接以及mq通道
		Connection connection = Tools.getConnection();
		Channel channel = connection.createChannel();

		// 声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		//绑定队列到交换机 
		//参数1：队列的名称
		//参数2：交换机的名称
		//参数3：routingKey（接收该EXCHANGE_NAME上发送的routingKey为A的消息）
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "A");

		// 同一时刻服务器只会发一条消息给消费者
		channel.basicQos(1);

		// 定义队列的消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		// 监听队列，手动返回完成
		channel.basicConsume(QUEUE_NAME, false, consumer);

		// 获取消息
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(" [消费者1] Received '" + message + "'");
			Thread.sleep(10);

			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
	}
}
