package com.zettamine.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MessageDaoImpl implements MessageDao {

	@Override
	public String getMessage() {
		return "Welcome to Zettamine";
	}

}
