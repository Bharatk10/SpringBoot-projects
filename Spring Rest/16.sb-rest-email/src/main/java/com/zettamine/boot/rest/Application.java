package com.zettamine.boot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.zettamine.boot.rest.services.EmailServices;

@SpringBootApplication
public class Application {
	@Autowired
	private EmailServices emailService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void sendTemplateEmail() {
		String to = "sunilozhakal@hotmail.com";
				
		boolean status = emailService.sendEmail(to);
		System.out.println(status);
	}
}
