package io.jms.receiver.receiver.impl;

import io.jms.receiver.entities.bean.CountBean;
import io.jms.receiver.receiver.AbstractJmsBaseListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

public class CountListener extends AbstractJmsBaseListener {

    private Logger log = Logger.getLogger(CountListener.class);

    /* (非 Javadoc)
     * @see jp.ameba.jms.receiver.listener.AbstractJmsBaseListener#onMessage(javax.jms.Message)
     */
    @Override
    public void onMessage(Message mes) {
        if(mes instanceof ObjectMessage){
            ObjectMessage objMessage = (ObjectMessage) mes;
            try {
                CountBean bean = (CountBean) objMessage.getObject();
                log.info("receive object ID:" + bean.getCount());
            } catch (JMSException e) {
                log.error("invalid Object",e);
            }
        }
    }

}