package io.jms.sender.main;

import io.jms.sender.simple.SimpleObjectJmsSender;

import javax.jms.JMSException;

public class MessageSender {

    public static void main(String[] args) {
        try {
            SimpleObjectJmsSender sender = new SimpleObjectJmsSender("tcp://localhost:61616","MessageQueue");

            String msg = "test message";
            sender.send(msg);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}