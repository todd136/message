package com.common.msgTest;

import com.common.msg.MqSendClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * Created by todd on 15/11/12.
 */
public class MqSendClientTest {
    MqSendClient mqSendClient;

    @Before
    public void setUp() throws Exception {
        mqSendClient = new MqSendClient();
    }

    @After
    public void tearDown() throws Exception {
        mqSendClient.release();
    }

    @Test
    public void testSendMsgToQueue() throws Exception {
        try {
            Random random = new Random();
            StringBuffer stringBuffer = new StringBuffer("线程").append(random.nextInt());
            stringBuffer.append("发送消息");
            for (int j = 0; j < 1000000; ++j) {
                mqSendClient.sendMsgToQueue(stringBuffer.toString() + j);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}