package com.zettamine.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.rest.entity.MailMessage;
import com.zettamine.rest.service.MailService;

@RestController
@RequestMapping("/zetta")
public class MailController {
	
	private MailService mailService;
	
	
	
	public MailController(MailService mailService) {
		super();
		this.mailService = mailService;
	}



	@PostMapping("/sendEmail")
	public ResponseEntity<?> sendEmail(@RequestBody MailMessage mail){
		boolean status = mailService.sendEmail(mail);
		
		if(status)
		
			return new ResponseEntity<Boolean>(status,HttpStatus.OK);
		
		return new ResponseEntity<String>("the email sending is failed",HttpStatus.OK);
	}

}
