package io.jms.sender.poolable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class PoolableTextJmsSender extends PoolableJmsSenderBase {

    @Override
    public Message createMessage(Session session, Object obj) throws JMSException {
    	return session.createTextMessage((String)obj);
    }

}
