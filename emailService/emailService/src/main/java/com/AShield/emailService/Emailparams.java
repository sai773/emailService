package com.AShield.emailService;

import java.util.Arrays;
import java.util.Date;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Emailparams {
	
	private String to_addr;
	private String cc;
	private String bcc_list;
	private String subject;
	private String htmlBody;
	private String button_text;
	private String button_link;

}


