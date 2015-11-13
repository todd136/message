package com.common.msg;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by todd on 15/11/11.
 */
public class MqReceiveClient {
    public static final String QUEUE_NAME = "hello";
    private final ConnectionFactory CONNECTION_FACTORY;
    private Connection connection;
    private Channel CHANNEL;

    public MqReceiveClient() {
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

    public String receiveMsgFromQueue() throws IOException, TimeoutException {
        GetResponse response = CHANNEL.basicGet(QUEUE_NAME, true);
        String message = new String(response.getBody());

        return message;
    }

    public void release()
    {
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
