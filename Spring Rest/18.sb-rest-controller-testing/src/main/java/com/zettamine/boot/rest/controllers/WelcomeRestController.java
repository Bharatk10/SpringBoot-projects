package com.zettamine.boot.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.boot.rest.services.WelcomeService;

@RestController
public class WelcomeRestController {
	@Autowired
	private WelcomeService welService;
	
	@GetMapping("/welcome")
	public String sendWelcomeMessage() {
		String msg = welService.getWelcomeMessage();
		return msg;
	}
}
