package com.AShield.emailService;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;


@Service
public class Emailmainsvc  {

	@Autowired
    private JavaMailSender emailSender;
	
	
	@Bean
	public VelocityEngine velocityEngine() throws Exception {
	    Properties properties = new Properties();
	    properties.setProperty("input.encoding", "UTF-8");
	    properties.setProperty("output.encoding", "UTF-8");
	    properties.setProperty("resource.loader", "class");
	    properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	    VelocityEngine velocityEngine = new VelocityEngine(properties);
	    return velocityEngine;
	}

	@Lazy
	@Autowired
	private VelocityEngine velocityEngine;
	
    @Async
    public Future<String> process(Emailparams params)throws MessagingException, IOException{  
        try {
        	
        	String returstring=null;
    		javax.mail.internet.MimeMessage message = emailSender.createMimeMessage();
    		//mailsender api
    		MimeMessageHelper mailSender = new MimeMessageHelper(message, true, "utf-8");

    		//mail fields
    		if(params.getTo_addr()!=null) {
    			
    	//check for without cc and bcc_list
    	if(params.getCc()!=null && params.getBcc_list()!=null) {
            System.out.println("start");

    		mailSender.setTo(InternetAddress.parse(params.getTo_addr()));
            mailSender.setSubject(params.getSubject());
            mailSender.setBcc(InternetAddress.parse(params.getBcc_list()));
            mailSender.setCc(InternetAddress.parse(params.getCc()));
        //    returstring="CC and Bcc_list valid details";
    		
    		}else 
    			//check for with cc and without bcc_list

    			if(params.getCc()!=null && params.getBcc_list()==null) {

    			mailSender.setTo(InternetAddress.parse(params.getTo_addr()));
    	        mailSender.setSubject(params.getSubject());
    	        // mailSender.setBcc(InternetAddress.parse(params.getBcc_list()));
    	        mailSender.setCc(InternetAddress.parse(params.getCc()));
    	     //   returstring="CC exist valid details";

    				}
    			//check for without cc and with bcc_list
    			else if(params.getCc()==null && params.getBcc_list()!=null) {
    				
    					mailSender.setTo(InternetAddress.parse(params.getTo_addr()));
    					mailSender.setSubject(params.getSubject());
    					mailSender.setBcc(InternetAddress.parse(params.getBcc_list()));
    					returstring="Bcc_list exist valid details";      		
    			}
    		else if(params.getTo_addr()!=null){
    			
   			mailSender.setTo(InternetAddress.parse(params.getTo_addr()));
   		    mailSender.setSubject(params.getSubject());
			returstring="To_address only exist";      		
       				
  		}else {
  			returstring="To_address not exist";
  		}

            //map function fo velocity engine
            VelocityContext velocityContext = new VelocityContext();
            
            velocityContext.put("htmlbody",params.getHtmlBody());
            velocityContext.put("button_name", params.getButton_text());
            velocityContext.put("button_val", params.getButton_link());
            
            
            if(params.getTo_addr()!=null && params.getButton_text()!=null && params.getButton_link()!=null) {

            StringWriter stringWriter = new StringWriter();
            velocityEngine.mergeTemplate("templates/mailbutton.html", "UTF-8", velocityContext, stringWriter);
            mailSender.setText(stringWriter.toString(), true);
			returstring="button and link exist";      		

           
            }else {

            	 StringWriter stringWriter = new StringWriter();
                 velocityEngine.mergeTemplate("templates/mail.html", "UTF-8", velocityContext, stringWriter);
                 mailSender.setText(stringWriter.toString(), true);
                 returstring="button not exist";      		

            }
    		}
         
            emailSender.send(message);
            Thread.sleep(15 * 1000);
            return new AsyncResult<String>(returstring);
            
            
           
        }
        catch (InterruptedException e) {
        	 throw new MailParseException(e);
        }
      // return "message sent succefully";
        
    }
}