package com.zettamine.boot.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zettamine.boot.rest.controllers.WelcomeRestController;
import com.zettamine.boot.rest.services.WelcomeService;

@SpringBootTest
class ApplicationTests {
	@Autowired
	WelcomeService service;
	
	@Autowired
	WelcomeRestController controller;
	
	@Test
	void contextLoadsService() {
		Assertions.assertThat(service).isNotNull();
	}
	@Test
	void contextLoadsController() {
		Assertions.assertThat(controller).isNotNull();
	}
}
