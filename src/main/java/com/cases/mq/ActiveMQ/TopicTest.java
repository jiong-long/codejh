package com.cases.mq.ActiveMQ;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import javax.jms.*;
import java.util.Map;

/**
 * @description: Topic测试
 *  1对多，群发
 * @author: OF3848
 * @create: 2020-11-28 15:32
 */
public class TopicTest {

    @Test
    public void TestTopicProducer() throws Exception{
        //1,2,3、获取连接
        Connection connection = ActiveMQConfig.getConnection();
        //4、使用连接对象创建会话（session）对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
        Topic topic = session.createTopic(ActiveMQConfig.TOPIC_NAME);
        //6、使用会话对象创建生产者对象
        MessageProducer producer = session.createProducer(topic);
        //7、使用会话对象创建一个消息对象
        TextMessage textMessage = session.createTextMessage("hello!test-topic");
        //8、发送消息
        producer.send(textMessage);
        //9、关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void TestTopicConsumer() throws Exception{
        //1,2,3、获取连接
        Connection connection = ActiveMQConfig.getConnection();
        //4、使用连接对象创建会话（session）对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
        Topic topic = session.createTopic(ActiveMQConfig.TOPIC_NAME);
        //6、使用会话对象创建生产者对象
        MessageConsumer consumer = session.createConsumer(topic);
        //7、向consumer对象中设置一个messageListener对象，用来接收消息
        consumer.setMessageListener(new MessageListener() {

            @Override
            public void onMessage(Message message) {
                try {
                    if(message instanceof TextMessage){
                        TextMessage textMessage = (TextMessage)message;
                        System.out.println(textMessage.getText());
                    } else if(message instanceof ObjectMessage){
                        String text = JSON.toJSONString(((ObjectMessage) message).getObject());
                        Map map = JSON.parseObject(text, Map.class);
                        System.out.println(text);
                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //8、程序等待接收用户消息
        System.in.read();
        //9、关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}
