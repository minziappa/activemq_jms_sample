package io.jms.receiver.receiver.impl;

import io.jms.receiver.receiver.AbstractJmsBaseListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextListener extends AbstractJmsBaseListener {

    private Logger logger = LoggerFactory.getLogger(TextListener.class);

    @Override
    public void onMessage(Message mes) {
        if(mes instanceof ObjectMessage){
            ObjectMessage objMessage = (ObjectMessage) mes;
            try {
            	String str = (String) objMessage.getObject();
            	logger.info("The message is " + str);
            } catch (JMSException e) {
            	logger.error("invalid Object",e);
            }
        }
    }

}
