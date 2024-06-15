package com.zettamine.rest.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.zettamine.rest.service.WelcomeService;

@WebMvcTest(value = WelcomeRestController.class)
class WelcomeRestControllerTest {

	@MockBean
	private WelcomeService welcomeService;

	@Autowired
	private MockMvc mockMvc;
	
	 @Test
	 public void testWelcomeMsg() throws Exception{
		
		Mockito.when(welcomeService.getWelcomeMsg()).thenReturn("Welcome to Zettamine..!!");
	}
}
