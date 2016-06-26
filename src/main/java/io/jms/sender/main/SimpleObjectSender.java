package io.jms.sender.main;

import io.jms.receiver.entities.bean.CountBean;
import io.jms.sender.simple.SimpleObjectJmsSender;

import javax.jms.JMSException;

public class SimpleObjectSender {

    public static void main(String[] args) {
        try {
            SimpleObjectJmsSender sender = new SimpleObjectJmsSender("tcp://localhost:61616","objectQueue");

            // Object
            CountBean cb = new CountBean();
            cb.setCount(10);

            sender.send(cb);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}