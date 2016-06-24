package io.jms.sender.main;

import io.jms.receiver.entities.bean.CountBean;
import io.jms.sender.bean.Counter;
import io.jms.sender.poolable.PoolableJmsSenderFactory;
import io.jms.sender.poolable.PoolableObjectJmsSender;

public class PoolableObjectSender {

    public static void main(String[] args) throws Exception {

        PoolableJmsSenderFactory.init(5,"tcp://localhost:61616");
        PoolableObjectJmsSender sender = PoolableJmsSenderFactory.createObjectSender("objectQueue");

        CountBean bean = new CountBean();
        bean.setCount(Counter.getInstance().getCount());                  

        sender.send(bean);

        PoolableJmsSenderFactory.closeManager();
    }
}
