package com.AShield.emailService;

import java.io.IOException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ConditionalOnExpression("${my.controller.enabled:true}")
public class Emailsvc {
	
	
	@Autowired
	private Emailmainsvc emainsvc;
	
	@RequestMapping(value="/email",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String welcome(@RequestBody Emailparams params)throws MessagingException, IOException, InterruptedException, ExecutionException, TimeoutException{ 
		     Future<String> val=   emainsvc.process(params);
	  		return val.get();
		
	}

}
