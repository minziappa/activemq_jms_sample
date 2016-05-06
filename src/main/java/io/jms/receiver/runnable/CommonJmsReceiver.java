package io.jms.receiver.runnable;

import io.jms.receiver.receiver.AbstractJmsBaseListener;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonJmsReceiver implements Runnable {

    private Logger logger = LoggerFactory.getLogger(CommonJmsReceiver.class);

    private String url;
    private AbstractJmsBaseListener listener;
    private String queueName;

    /**
     * 
     */
    public CommonJmsReceiver() {
        this.url = ActiveMQConnection.DEFAULT_BROKER_URL;
    }
    
    /**
     * @param listener
     */
    public void setListener(AbstractJmsBaseListener listener) {
        this.listener = listener;
    }

    /**
     * @param queueName
     */
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    /* (非 Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run() {
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
            QueueConnection connection = factory.createQueueConnection();

            connection.setExceptionListener(listener);
              // Make a session
            QueueSession session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(queueName);
            // connect to Queue
            QueueReceiver receiver = session.createReceiver(queue);
            receiver.setMessageListener(listener);

            connection.start();

            while(!listener.isExit()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                	logger.error("Thread sleep fail",e);
                }
            }

            receiver.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
        	logger.error("Jms server is dead ?",e);
        }
    }

    public void listenerStop(){
        listener.exit();
    }

    /**
     * @param url
     */
    public void init(String url) {
        this.url = url;
    }
}
