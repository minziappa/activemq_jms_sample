package io.jms.sender.common;

import javax.jms.QueueConnection;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.StackObjectPool;

public class JmsConnectionManager {

    private StackObjectPool<QueueConnection> pool;

    public void init(int poolSize, BasePoolableObjectFactory<QueueConnection> factory) {
        pool = new StackObjectPool<QueueConnection>(factory, poolSize);
    }

    public QueueConnection getConnection() throws Exception{
        return (QueueConnection) pool.borrowObject();
    }

    public void returnConnection(QueueConnection con) throws Exception{
        pool.returnObject(con);
    }

    public void close() throws Exception{
        pool.close();
    }
}
