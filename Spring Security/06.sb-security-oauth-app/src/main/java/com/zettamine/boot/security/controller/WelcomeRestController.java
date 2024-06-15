package com.zettamine.boot.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeRestController {
	
	@GetMapping("/welcome")
	public String getWelcomePage() {
		
		return "<h1>Welcome to Zettamine</h1>";
	}

}
