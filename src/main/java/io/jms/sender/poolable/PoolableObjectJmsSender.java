package io.jms.sender.poolable;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class PoolableObjectJmsSender extends PoolableJmsSenderBase {

    @Override
    public Message createMessage(Session session, Object obj) throws JMSException {
        return session.createObjectMessage((Serializable)obj);
    }
}
