package io.jms.receiver.util;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

public class VelocityMerge {

    public static String merge(Map<String, String> map) 
    		throws ResourceNotFoundException, ParseErrorException, Exception{

    	VelocityContext context = new VelocityContext(map);
        StringWriter sw = new StringWriter();
        Template temp = Velocity.getTemplate("/test/template.vm");
        temp.merge(context,sw);
        sw.flush();
        return sw.toString();
    }

}
