package io.jms.sender.simple;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class SimpleTextJmsSender extends SimpleJmsSenderBase {

    public SimpleTextJmsSender() {
        super();
    }

    public SimpleTextJmsSender(String url, String queueName) throws JMSException {
        super(url,queueName);
    }

    @Override
    public Message createMessage(Session session, Object obj)
            throws JMSException {
        return session.createTextMessage((String)obj);
    }

}
