package com.zettamine.boot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zettamine.boot.util.PasswordEncryptor;

@Configuration
public class EmployeeConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeConfig.class);

	public EmployeeConfig() {
		super();
		LOGGER.info("Employeeconfig :: bean");
	}
	
	@Bean
	public PasswordEncryptor getEncoder() {
		return new PasswordEncryptor();
	}
	
	
}
