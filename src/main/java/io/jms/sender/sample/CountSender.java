package io.jms.sender.sample;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;

public class CountSender {

    public static void main(String[] args){
        int threads = 50;
        int sleepTime = 1000;
        long endTime = 8*60*60*1000;
        long timeOut = System.currentTimeMillis() + endTime;
        ScheduledExecutorService  es = Executors.newScheduledThreadPool(threads);

        try {
            QueueConnectionFactory factory = new ActiveMQConnectionFactory("tcp://172.17.2.52:61616");
            QueueConnection connection = factory.createQueueConnection();
            connection.start();

           for(int i=0;i<threads;i++){
                CounterThread run = new CounterThread(connection ,"CountQueue");
                es.scheduleAtFixedRate(run,0,1000,TimeUnit.MILLISECONDS);
            }
            while(timeOut >= System.currentTimeMillis()){
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Counter.getInstance().getCount());
            es.shutdownNow();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }           
    }
}
