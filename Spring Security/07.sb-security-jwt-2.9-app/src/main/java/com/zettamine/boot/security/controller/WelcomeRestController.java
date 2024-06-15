package com.zettamine.boot.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeRestController {
	
	@GetMapping("/welcome")	
	  public String showWelcomeMessage() {
		  return "Welcome to Zettamine";
	  }
	  
	  @GetMapping("/greet")
	  public String showGreetings() {
		  return "Greetings from Zettamine";
	  }

}
