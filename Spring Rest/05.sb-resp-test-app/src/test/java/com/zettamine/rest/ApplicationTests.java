package com.zettamine.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zettamine.rest.controller.WelcomeRestController;
import com.zettamine.rest.service.WelcomeService;

@SpringBootTest
class ApplicationTests {

	 @Autowired
	 WelcomeRestController controller;
		
	 @Autowired
	 WelcomeService service;
		
	 @Test
	 public void contextLoads() {
	  Assertions.assertThat(controller).isNotNull();	
	  Assertions.assertThat(service).isNotNull();
	 }

}
