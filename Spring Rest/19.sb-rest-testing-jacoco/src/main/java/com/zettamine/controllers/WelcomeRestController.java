package com.zettamine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.service.WelcomeService;

@RestController
public class WelcomeRestController {

	@Autowired
	private WelcomeService service;

	@GetMapping("/welcome")
	public ResponseEntity<String> getWelcomeMsg() {
		String msg = service.getMsg();
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@GetMapping("/greet")
	public ResponseEntity<String> getGreetMsg() {
		String msg = service.getGreetMsg();
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
