package com.zettamine.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zettamine.boot.configuration.DBConfiguration;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		
		DBConfiguration dbConfiguration = context.getBean(DBConfiguration.class);
		
		System.out.println(dbConfiguration);
		
	}
		

}
