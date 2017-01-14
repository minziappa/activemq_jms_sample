//package io.jms.receiver.receiver.impl;
//
//import java.util.Map;
//
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.ObjectMessage;
//
//import io.jms.receiver.mail.SendEmail;
//import io.jms.receiver.receiver.AbstractJmsBaseListener;
//import io.jms.receiver.util.VelocityMerge;
//
//import org.apache.commons.mail.EmailException;
//import org.apache.log4j.Logger;
//import org.apache.velocity.VelocityContext;
//import org.apache.velocity.exception.ParseErrorException;
//import org.apache.velocity.exception.ResourceNotFoundException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//public class EmailListener2 extends AbstractJmsBaseListener {
//
//    private Logger log = Logger.getLogger(EmailListener2.class);
//    private String messageVmPath;
//    private String subjectVmPath;
//    private String fromVmPath;
//
//    /**
//     * @param messageVmPath
//     */
//    public void setMessageVmPath(String messageVmPath) {
//        this.messageVmPath = messageVmPath;
//    }
//
//    /**
//     * @param subjectVmPath
//     */
//    public void setSubjectVmPath(String subjectVmPath) {
//        this.subjectVmPath = subjectVmPath;
//    }
//
//    /**
//     * @param fromVmPath
//     */
//    public void setFromVmPath(String fromVmPath) {
//        this.fromVmPath = fromVmPath;
//    }
//
//    /* (非 Javadoc)
//     * @see jp.ameba.jms.receiver.listener.AbstractJmsBaseListener#onMessage(javax.jms.Message)
//     */
//    @Override
//    public void onMessage(Message mes) {
//        if(mes instanceof ObjectMessage){
//            ObjectMessage objMessage = (ObjectMessage) mes;
//            try {
//                Map<String, String> map = (Map<String, String>)objMessage.getObject();
//
//                // Merge with Velocity
//                String msg;
//                String sub;
//                String from;
//                try {
//                    msg = mergeMap(map,messageVmPath);
//                    sub = mergeMap(map,subjectVmPath);
//                    from = mergeMap(map,fromVmPath);
//                }  catch (Exception e) {
//                    log.error("vm merge error",e);
//                    return;
//                }
//
//                // To send email
//        		ApplicationContext context = new ClassPathXmlApplicationContext("springConfig.xml");
//        		SendEmail email = (SendEmail) context.getBean("emailImpl");
//                email.addTo((String) map.get("to_address"));
//                email.setFrom(from);
//                email.setSubject(sub);
//                email.setMsg(msg);
//
//                email.send();
//            } catch (JMSException e) {
//                log.error("invalid Object",e);
//            } catch (EmailException e) {
//                log.error("mail error",e);
//            }
//        }
//    }
//
//    /**
//     * @param bean
//     * @return
//     * @throws Exception 
//     * @throws ParseErrorException 
//     * @throws ResourceNotFoundException 
//     */
//    private String mergeMap(Map map, String vmPath) 
//            throws ResourceNotFoundException, ParseErrorException, Exception {
//        VelocityContext context = new VelocityContext(map);
//        return VelocityMerge.merge(vmPath,context);
//    }
//
//}
