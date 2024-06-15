package com.zettamine.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	@GetMapping("/welcome")
	public String getWelcomePage() {
		
		return "<h1>Welcome to Zettamine</h1>";
	}

}
