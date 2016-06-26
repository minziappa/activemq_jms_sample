package io.jms.sender.main;

import io.jms.sender.simple.SimpleObjectJmsSender;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;

public class SimpleEmailSender {

    public static void main(String[] args) {
        try {
            SimpleObjectJmsSender sender = new SimpleObjectJmsSender("tcp://localhost:61616","emailQueue");

            Map<String, String> map = new HashMap<String, String>();
            map.put("to_address","test@gmail.com");
            sender.send(map);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}