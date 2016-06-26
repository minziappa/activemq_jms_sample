package io.jms.sender.main;

import io.jms.sender.poolable.PoolableJmsSenderFactory;
import io.jms.sender.poolable.PoolableTextJmsSender;

public class PoolableTextSender {

    public static void main(String[] args) throws Exception {

        PoolableJmsSenderFactory.init(5,"tcp://localhost:61616");
        PoolableTextJmsSender sender = PoolableJmsSenderFactory.createTextSender("textQueue");

        sender.send("This is message on pool");

        PoolableJmsSenderFactory.closeManager();
    }
}
