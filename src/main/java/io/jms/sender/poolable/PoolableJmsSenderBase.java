package io.jms.sender.poolable;

import io.jms.sender.JmsSenderInterface;
import io.jms.sender.common.JmsConnectionManager;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

public abstract class PoolableJmsSenderBase implements JmsSenderInterface {

    private JmsConnectionManager manager;
    private String queueName;

    /**
     * @param manager
     */
    public void setConnectionManager(JmsConnectionManager manager) {
        this.manager = manager;
    }

    /**
     * @param queueName
     */
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public void send(Object obj) throws JMSException {
        try {
            QueueConnection connection = manager.getConnection();

            QueueSession session = connection.createQueueSession(false,QueueSession.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(queueName);
            QueueSender sender = session.createSender(queue);
            
            Message msg = createMessage(session, obj);

            sender.send(msg);
            
            sender.close();
            session.close();
            manager.returnConnection(connection);

        } catch (JMSException e) {
            throw e;
        } catch (Exception e) {
            throw new JMSException("Connection pool error");
        }
    }

    public abstract Message createMessage(Session session, Object obj) throws JMSException;

}
