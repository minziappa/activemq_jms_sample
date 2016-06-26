package io.jms.sender.main;

import io.jms.sender.simple.SimpleObjectJmsSender;

import javax.jms.JMSException;

public class SimpleTextSender {

    public static void main(String[] args) {
        try {
            SimpleObjectJmsSender sender = new SimpleObjectJmsSender("tcp://localhost:61616","textQueue");
            String msg = "test message2";
            sender.send(msg);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}