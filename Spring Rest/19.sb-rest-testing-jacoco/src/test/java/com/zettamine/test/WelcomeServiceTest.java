package com.zettamine.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.zettamine.dao.MessageDao;
import com.zettamine.service.WelcomeService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WelcomeServiceTest {

	@MockBean
	private MessageDao dao;

	@InjectMocks
	private WelcomeService service;

	@Test
	public void testGetGreetMsg() {
		when(dao.getMessage()).thenReturn("Good Morning..!!");
		String greetMsg = service.getGreetMsg();
		assertNotNull(greetMsg);
	}

	@Test
	public void testGetMsg() {
		when(dao.getMessage()).thenReturn("Welcome to Zettamine..!!");
		String msg = service.getMsg();
		assertNotNull(msg);
	}
}