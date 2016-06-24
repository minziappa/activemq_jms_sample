package io.jms.sender.runnable;

import io.jms.receiver.entities.bean.CountBean;
import io.jms.sender.bean.Counter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;

public class ObjectRunnable implements Runnable {

    private String queueName;
    private QueueConnection connection;

    public ObjectRunnable(QueueConnection connection, String queueName){
        this.connection = connection;
        this.queueName = queueName;
    }

    public void run() {
        
        try {

            QueueSession session = connection.createQueueSession(false,QueueSession.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(queueName);
            QueueSender sender = session.createSender(queue);
            sender.setTimeToLive(15000);

            CountBean bean = new CountBean();
            bean.setCount(Counter.getInstance().getCount());                  

            Message msg = session.createObjectMessage(bean);
            
            sender.send(msg);
            
            sender.close();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } 
    }
    
    
}
