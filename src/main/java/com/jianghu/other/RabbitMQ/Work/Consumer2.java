package com.jianghu.other.RabbitMQ.Work;

import java.io.IOException;

import com.jianghu.core.tools.Log;
import com.jianghu.other.RabbitMQ.RabbitTools;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

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

		
		// 定义队列的消费者
		Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [消费者2] Received '" + message + "'");
                channel.basicAck(envelope.getDeliveryTag(), false);
                // 休眠1秒
    			try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Log.error(e);
				}
            }
        };
        
        //监听队列
  		//参数1：队列名称
  		//参数2：是否发送ack包，不发送ack消息会持续在服务端保存，直到收到ack。可以通过channel.basicAck手动回复ack，见Work模式下的Consumer1.java
  		//参数3：消费者
  		channel.basicConsume(QUEUE_NAME, false, consumer);
	}
}
