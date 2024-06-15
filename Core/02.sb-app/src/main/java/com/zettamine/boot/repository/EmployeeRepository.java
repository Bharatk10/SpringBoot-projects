package com.zettamine.boot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRepository.class);

	public EmployeeRepository() {
		
		LOGGER.info(System.lineSeparator());
		LOGGER.info("EmployeeRepository :: Bean");
	}
	
	

}
