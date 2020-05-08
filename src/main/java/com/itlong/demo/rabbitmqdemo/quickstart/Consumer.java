package com.itlong.demo.rabbitmqdemo.quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 消费者
 */
public class Consumer {

    public static void main(String[] args) throws  Exception {
        //1.创建连接工厂
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("192.168.2.128");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("rabbit");
        connectionFactory.setPassword("123456");
        //2.创建连接
        Connection connection= connectionFactory.newConnection();
        //3 创建通道
        Channel channel= connection.createChannel();
        //4.声明一个队列
        String queueName="test01";
        // 队列名  是否持久化
        //(queue, durable, exclusive, autoDelete, args)
        channel.queueDeclare(queueName,true,false,false,null);
        //5. 创建消费者
        QueueingConsumer queueingConsumer=new QueueingConsumer(channel);
        //6.设置通道(String queue, boolean autoAck,Consumer callback)
        channel.basicConsume(queueName,true,queueingConsumer);
        while (true){
            //7.获取消息
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            byte[] body = delivery.getBody();
            System.out.println(new String(body));
        }
    }
}
