package io.jms.sender;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public interface JmsSenderInterface {

    public void send(Object obj) throws JMSException;

    public Message createMessage(Session session, Object obj) throws JMSException;

}