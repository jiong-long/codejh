package com.cases.mq.RabbitMQ.routing;

import com.cases.mq.RabbitMQ.RabbitTools;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer1 {

	private final static String QUEUE_NAME = "test_queue_direct_1";

	private final static String EXCHANGE_NAME = "test_exchange_direct";

	public static void main(String[] argv) throws Exception {
		// 获取到连接以及mq通道
		Connection connection = RabbitTools.getConnection();
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
		Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [消费者1] Received '" + message + "'");
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

		//监听队列
		//参数1：队列名称
		//参数2：是否发送ack包，不发送ack消息会持续在服务端保存，直到收到ack。可以通过channel.basicAck手动回复ack，见Work模式下的Consumer1.java
		//参数3：消费者
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}
}
