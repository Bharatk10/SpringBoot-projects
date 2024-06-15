package com.zettamine.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zettamine.boot.entity.Employee;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
		final Logger LOGGER = LoggerFactory.getLogger("com.zettamine.boot.Application");
		try(ConfigurableApplicationContext context = SpringApplication.run(Application.class, args)){
			
			int noOfBeans = context.getBeanDefinitionCount();
			LOGGER.info(System.lineSeparator());
			
			Employee emp = context.getBean(Employee.class);
			
			System.out.println(emp);
			
			LOGGER.info("noOfBeans {}",noOfBeans);
		}
	}

}
