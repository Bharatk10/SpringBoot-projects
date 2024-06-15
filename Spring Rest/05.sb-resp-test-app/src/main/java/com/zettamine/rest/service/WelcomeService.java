package com.zettamine.rest.service;

import org.springframework.stereotype.Service;

@Service
public class WelcomeService {
	
	public String getWelcomeMsg(){
		   return "Welcome to Spring Boot REST Testing";
		}

}
