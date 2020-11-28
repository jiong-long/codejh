package com.cases.mq.RabbitMQ.pubsub;

import com.cases.mq.RabbitMQ.RabbitTools;
import com.common.Log;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer2 {
	private final static String QUEUE_NAME = "test_queue_exchange_2";
	private final static String EXCHANGE_NAME = "test_exchange_fanout";

	public static void main(String[] argv) throws Exception {
		Connection connection = RabbitTools.getConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

		channel.basicQos(1);
		
		// 定义队列的消费者
		Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [消费者2] Received '" + message + "'");
                channel.basicAck(envelope.getDeliveryTag(), false);
                try {
					Thread.sleep(10L);
				} catch (InterruptedException e) {
					Log.error(e);
				}
            }
        };
        
        // 监听队列，手动返回完成
     	channel.basicConsume(QUEUE_NAME, false, consumer);
	}
}
