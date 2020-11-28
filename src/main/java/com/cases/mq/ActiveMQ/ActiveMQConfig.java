package com.cases.mq.ActiveMQ;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

/**
 * @description: 配置类
 * @author: OF3848
 * @create: 2020-11-28 15:44
 */
public class ActiveMQConfig {
    public static final String ACTIVE_MQ_URL = "tcp://127.0.0.1:61616";

    public static final String QUEUE_NAME = "test-queue";

    public static final String TOPIC_NAME = "test-topic";

    /**
     * 获取一个连接
     * @return
     * @throws JMSException
     */
    public static Connection getConnection() throws JMSException {
        //1、创建工厂连接对象，需要制定ip和端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConfig.ACTIVE_MQ_URL);
        //2、使用连接工厂创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        //3、开启连接
        connection.start();

        return connection;
    }
}
