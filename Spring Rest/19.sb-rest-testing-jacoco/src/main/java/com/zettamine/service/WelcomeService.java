package com.zettamine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zettamine.dao.MessageDao;

@Service
public class WelcomeService {

	@Autowired
	private MessageDao dao;

	public String getMsg() {
		String msg = dao.getMessage();
		return msg;
	}

	public String getGreetMsg() {
		String msg = dao.getMessage();
		return msg;
	}

}
