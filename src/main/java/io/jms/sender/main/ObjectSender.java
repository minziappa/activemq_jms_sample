package io.jms.sender.main;

import io.jms.receiver.entities.bean.CountBean;
import io.jms.sender.bean.Counter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author aono_masashi_gn
 *
 */
public class ObjectSender {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        try {
        	// Make factory and start
            QueueConnectionFactory factory = new ActiveMQConnectionFactory("failover://(tcp://localhost:61616,tcp://localhost:61616)?randomize=false");
            QueueConnection connection = factory.createQueueConnection();
            connection.start();

            // Make session
            QueueSession session = connection.createQueueSession(false,QueueSession.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("objectQueue");
            QueueSender sender = session.createSender(queue);
            sender.setTimeToLive(15000);            
            
            CountBean bean = new CountBean();
            bean.setCount(Counter.getInstance().getCount());                  

            Message msg = session.createObjectMessage(bean);
            
            sender.send(msg);
            
            sender.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
