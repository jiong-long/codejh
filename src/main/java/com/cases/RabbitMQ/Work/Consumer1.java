package com.cases.RabbitMQ.Work;

import java.io.IOException;

import com.cases.RabbitMQ.RabbitTools;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * 消费者1
 * 
 * @creatTime 2018年10月30日 下午9:48:15
 * @author jinlong
 */
public class Consumer1 {

	private final static String QUEUE_NAME = "test_queue_work";

	public static void main(String[] argv) throws Exception {
		// 获取到连接以及mq通道
		Connection connection = RabbitTools.getConnection();
		Channel channel = connection.createChannel();

		// 声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		// 同一时刻服务器只会发一条消息给该消费者(能者多劳模式)
		// 该消费者在接收到队列里的消息但没有返回确认结果之前,服务器不会将新的消息分发给它。
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
		channel.basicConsume(QUEUE_NAME, false, consumer);
	}
}
