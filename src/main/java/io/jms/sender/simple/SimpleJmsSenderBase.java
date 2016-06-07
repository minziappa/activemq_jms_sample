package io.jms.sender.simple;

import io.jms.sender.JmsSenderInterface;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public abstract class SimpleJmsSenderBase implements JmsSenderInterface {

    private String url;
    private String queueName;
    
    /**
     * 
     */
    public SimpleJmsSenderBase() {
        this.url = ActiveMQConnection.DEFAULT_BROKER_URL;
    }
    
    /**
     * @param url 
     * @param queueName 
     * @throws JMSException 
     * 
     */
    public SimpleJmsSenderBase(String url,String queueName) throws JMSException {
        this.url = url;
        this.queueName = queueName;
    }

    /* (非 Javadoc)
     * @see jp.ameba.jms.sender.JmsSenderInterface#send(java.lang.Object)
     */
    public void send(Object obj) throws JMSException{

        QueueConnectionFactory factory = new ActiveMQConnectionFactory(url);
        QueueConnection connection = factory.createQueueConnection();
        connection.start();

        QueueSession session = connection.createQueueSession(false,QueueSession.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(queueName);
        QueueSender sender = session.createSender(queue);
        
        Message msg = createMessage(session, obj);

        sender.send(msg);

        sender.close();
        session.close();
        connection.close();
    }

    /* (非 Javadoc)
     * @see jp.ameba.jms.sender.JmsSenderInterface#createMessage(javax.jms.Session, java.lang.Object)
     */
    public abstract Message createMessage(Session session, Object obj) throws JMSException;
}
