package com.common.msgTest;

import com.common.msg.MqReceiveClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by todd on 15/11/12.
 */
public class MqReceiveClientTest {
    MqReceiveClient mqReceiveClient;

    @Before
    public void setUp() throws Exception {
        mqReceiveClient = new MqReceiveClient();
    }

    @After
    public void tearDown() throws Exception {
        mqReceiveClient.release();
    }

    @Test
    public void testReceiveMsgFromQueue() throws Exception {
        for (int i = 0; i < 10; ++i) {
            Thread thread = new Thread() {
                public void run() {
                    String msg;
                    while (true) {
                        try {
                            msg = mqReceiveClient.receiveMsgFromQueue();
                            System.out.println(this.getName() + msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (TimeoutException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };

            thread.run();
        }
    }
}