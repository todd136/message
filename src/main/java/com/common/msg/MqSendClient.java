package com.common.msg;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by todd on 15/11/11.
 */
public class MqSendClient {
    private static final String QUEUE_NAME = "hello";
    private final ConnectionFactory CONNECTION_FACTORY;
    private Connection connection;
    private Channel CHANNEL;

    public MqSendClient()
    {
        CONNECTION_FACTORY = new ConnectionFactory();
        CONNECTION_FACTORY.setHost("localhost");
        try {
            connection = CONNECTION_FACTORY.newConnection();
            CHANNEL = connection.createChannel();
            CHANNEL.queueDeclare(QUEUE_NAME, true, false, false, null);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void sendMsgToQueue(String msg) throws IOException, TimeoutException {
        CHANNEL.basicPublish("", QUEUE_NAME, null, msg.getBytes());
    }

    public void release(){
        try {
            CHANNEL.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
