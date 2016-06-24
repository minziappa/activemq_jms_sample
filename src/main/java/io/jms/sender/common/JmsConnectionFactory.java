package io.jms.sender.common;

import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;

import org.apache.commons.pool.BasePoolableObjectFactory;

public class JmsConnectionFactory extends BasePoolableObjectFactory<QueueConnection> {

    private QueueConnectionFactory factory;

    public JmsConnectionFactory(QueueConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public QueueConnection makeObject() throws Exception {
        QueueConnection con = factory.createQueueConnection();
        con.start();
        return con;
    }

    @Override
    public void destroyObject(QueueConnection obj) throws Exception {
        QueueConnection con = (QueueConnection) obj;
        con.close();
    }
}
