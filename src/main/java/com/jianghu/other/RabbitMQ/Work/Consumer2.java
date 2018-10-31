package com.jianghu.other.RabbitMQ.Work;

import com.jianghu.other.RabbitMQ.RabbitTools;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 消费者2
 * 
 * @creatTime 2018年10月30日 下午9:48:23
 * @author jinlong
 */
public class Consumer2 {

	private final static String QUEUE_NAME = "test_queue_work";

	public static void main(String[] argv) throws Exception {
		Connection connection = RabbitTools.getConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		channel.basicQos(1);

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, false, consumer);

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(" [消费者2] Received '" + message + "'");
			// 休眠1秒
			Thread.sleep(1000);
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
	}
}
