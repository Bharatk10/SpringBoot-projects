package com.zettamine.boot.rest.services;

import org.springframework.stereotype.Service;

@Service
public class WelcomeService {
	public String getWelcomeMessage() {
		return "Welcome to Spring Boot Unit Testing"; 
	}
}
