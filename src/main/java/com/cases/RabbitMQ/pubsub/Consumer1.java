package com.cases.RabbitMQ.pubsub;

import java.io.IOException;

import com.common.Log;
import com.cases.RabbitMQ.RabbitTools;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Consumer1 {
	private final static String QUEUE_NAME = "test_queue_exchange_1";
	private final static String EXCHANGE_NAME = "test_exchange_fanout";

	public static void main(String[] argv) throws Exception {

		// 获取到连接以及mq通道
		Connection connection = RabbitTools.getConnection();
		Channel channel = connection.createChannel();

		// 声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		//绑定队列到交换机(这个交换机的名称一定要和上面的生产者交换机名称相同)
		//参数1：队列的名称
		//参数2：交换机的名称
		//参数3：Routing Key
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

		// 同一时刻服务器只会发一条消息给消费者
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
