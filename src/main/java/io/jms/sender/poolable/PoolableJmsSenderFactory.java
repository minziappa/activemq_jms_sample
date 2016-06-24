package io.jms.sender.poolable;

import io.jms.sender.common.JmsConnectionFactory;
import io.jms.sender.common.JmsConnectionManager;

import org.apache.activemq.ActiveMQConnectionFactory;

public class PoolableJmsSenderFactory {

    private static JmsConnectionManager man;
    
    private PoolableJmsSenderFactory(){    
    }
    
    /**
     * 
     * @param poolSize
     * @param url
     */
    public static void init(int poolSize, String url){
        synchronized (man) {
            if(man == null){
                man = new JmsConnectionManager();
                man.init(poolSize, new JmsConnectionFactory(new ActiveMQConnectionFactory(url)));
            }
        }
    }
    
    /**
     * 
     * @throws Exception
     */
    public static void closeManager() throws Exception{
        if(man != null){
            man.close();
        }
    }
    
    /**
     * 
     * @param queueName
     * @return
     */
    public static PoolableObjectJmsSender createObjectSender(String queueName){
        PoolableObjectJmsSender sender = new PoolableObjectJmsSender();
        sender.setQueueName(queueName);
        sender.setConnectionManager(man);
        
        return sender;
    }
    
    /**
     * 
     * @param queueName
     * @return
     */
    public static PoolableTextJmsSender createTextSender(String queueName){
        PoolableTextJmsSender sender = new PoolableTextJmsSender();
        sender.setQueueName(queueName);
        sender.setConnectionManager(man);
        
        return sender;
    }
}
