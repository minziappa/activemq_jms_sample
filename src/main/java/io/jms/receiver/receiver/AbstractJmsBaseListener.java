package io.jms.receiver.receiver;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractJmsBaseListener implements ExceptionListener, MessageListener {

    Logger logger = LoggerFactory.getLogger(AbstractJmsBaseListener.class);

    private boolean exitFlg = false;

    /* (非 Javadoc)
     * @see javax.jms.ExceptionListener#onException(javax.jms.JMSException)
     */
    public void onException(JMSException e){
    	logger.warn("JMS Exception is occurred ! ",e);
    }

    /* (非 Javadoc)
     * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
     */
    public abstract void onMessage(Message mes);

    public final boolean isExit(){
        return exitFlg;
    }

    public final void exit(){
        synchronized (this) {
            exitFlg = true;
        }
    }
}
