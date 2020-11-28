package com.cases.mq.RabbitMQ.HelloWorld;

import com.cases.mq.RabbitMQ.RabbitTools;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 消费者
 * 
 * @creatTime 2018年10月29日 下午10:16:00
 * @author jinlong
 */
public class Consumer {

	private final static String QUEUE_NAME = "HelloWorld";

	public static void main(String[] argv) throws Exception {
		// 获取到连接以及mq通道
		Connection connection = RabbitTools.getConnection();
		// 从连接中创建通道
		Channel channel = connection.createChannel();

		// 声明队列(如果你已经明确的知道有这个队列,那么下面这句代码可以注释掉,如果不注释掉的话,也可以理解为消费者必须监听一个队列,如果没有就创建一个)
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		// 定义队列的消费者
		com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [消费者2] Received '" + message + "'");
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
