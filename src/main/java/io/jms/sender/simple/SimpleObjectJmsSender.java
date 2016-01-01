package io.jms.sender.simple;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class SimpleObjectJmsSender extends SimpleJmsSenderBase {

    public SimpleObjectJmsSender() {
        super();
    }

    public SimpleObjectJmsSender(String url, String queuName) throws JMSException {
        super(url,queuName);
    }

    @Override
    public Message createMessage(Session session, Object obj) throws JMSException {
        return session.createObjectMessage((Serializable)obj);
    }


}
