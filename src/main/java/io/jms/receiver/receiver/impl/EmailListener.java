package io.jms.receiver.receiver.impl;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import io.jms.receiver.receiver.AbstractJmsBaseListener;
import org.apache.log4j.Logger;

public class EmailListener extends AbstractJmsBaseListener {

    private Logger log = Logger.getLogger(EmailListener.class);

    /* (非 Javadoc)
     * @see jp.ameba.jms.receiver.listener.AbstractJmsBaseListener#onMessage(javax.jms.Message)
     */
    @Override
    public void onMessage(Message mes) {
        if(mes instanceof ObjectMessage){
            ObjectMessage objMessage = (ObjectMessage) mes;
            try {
                @SuppressWarnings("unchecked")
				Map<String, String> map = (Map<String, String>)objMessage.getObject();

                // To send email
                String strEmailAddress = map.get("to_address");
                log.info("email address >>> " + strEmailAddress);

            } catch (JMSException e) {
                log.error("invalid Object",e);
            }
        }
    }

}
