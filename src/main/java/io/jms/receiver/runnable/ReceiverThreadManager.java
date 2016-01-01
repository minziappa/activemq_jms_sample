package io.jms.receiver.runnable;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class ReceiverThreadManager implements Runnable {

	private Logger logger = LoggerFactory.getLogger(ReceiverThreadManager.class);

	@Autowired
    private Configuration config;
	@Autowired
    private List<CommonJmsReceiver> receiverList;
	@Autowired
    private File stopFile;
	@Value("${jms.url}")
    private String jmsUrl;

    public void execute(){
    	logger.info("JMS Receiver start !");

        try {
//            Properties prop = new Properties();
//            prop.load(
//                    Thread.currentThread().getContextClassLoader()
//                          .getResourceAsStream("resources/velocity.properties"));
//            Velocity.init(prop);
        } catch (Exception e) {
        	logger.error("Velocity init error",e);
            return;
        }

        Iterator<CommonJmsReceiver> ite = receiverList.iterator();
        while(ite.hasNext()){
            CommonJmsReceiver receiver = (CommonJmsReceiver) ite.next();
            logger.info("url >> " + jmsUrl);
            receiver.init(jmsUrl);
            Thread th = new Thread(receiver);
            th.start();
        }
        Thread man = new Thread(this);
        man.start();
    }

    /* (非 Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run() {
        while(!stopFile.exists()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Iterator<CommonJmsReceiver> ite = receiverList.iterator();
        while(ite.hasNext()){
            CommonJmsReceiver receiver = (CommonJmsReceiver) ite.next();
            receiver.listenerStop();
        }
        logger.info("JMS Receiver stop !");
    }
}
