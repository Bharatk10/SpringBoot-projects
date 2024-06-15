package com.zettamine.boot.rest.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.zettamine.boot.rest.controllers.WelcomeRestController;
import com.zettamine.boot.rest.services.WelcomeService;

@WebMvcTest(value = WelcomeRestController.class)
class WelcomeRestControllerTest {
	@MockBean   //to create a mock object/ substitute object
    private WelcomeService welService;
    
	@Autowired // to perform REST API Calls
	private MockMvc mockMvc;
	
	@Test
	void testSendWelcomeMessage() throws Exception {
		/*
		//define mock obj behaviour
		Mockito.when(welService.getWelcomeMessage()).thenReturn("Welcome");

		// prepare http get request
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/welcome"); //to prepare an HTTP Request
		
		// send request
		ResultActions perform = mockMvc.perform(reqBuilder); //sending a request to REST API
		
		// hold the response
		MvcResult mvcResult = perform.andReturn(); //return the result of the executed request for direct access to the results
		
		// validate response
		MockHttpServletResponse httpResponse = mvcResult.getResponse();
		String responseData = httpResponse.getContentAsString();
		int responseStatus = httpResponse.getStatus();
		
		//assertNotNull(responseData);
		assertEquals(200, responseStatus);
		*/
		
		Mockito.when(welService.getWelcomeMessage()).thenReturn("Welcome");
		mockMvc.perform(MockMvcRequestBuilders.get("/welcome"))
			   .andExpect(MockMvcResultMatchers.status().isOk());	
	}

}
