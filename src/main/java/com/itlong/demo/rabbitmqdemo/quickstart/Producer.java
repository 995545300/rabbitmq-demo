package com.itlong.demo.rabbitmqdemo.quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 生产者
 */
public class Producer {

    public static void main(String[] args) throws Exception {

        //1.创建连接工厂,并配置host port
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("192.168.2.128");
        connectionFactory.setUsername("rabbit");
        connectionFactory.setPassword("123456");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        //2.通过连接工厂创建廉价而
        Connection connection = connectionFactory.newConnection();
        //3.通过连接创建数据通道
        Channel channel= connection.createChannel();

        //4.通过channel 发送数据
        String msg="Hello world RabbitMQ ";
        //参数1 exchange   参数2 routingKey
        channel.basicPublish("","test01",null,msg.getBytes());

        //5 关闭连接
        channel.close();
        connection.close();

    }

}
