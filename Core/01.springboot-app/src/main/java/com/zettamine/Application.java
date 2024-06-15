package com.zettamine;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		
		int noOfBeans = context.getBeanDefinitionCount();
		System.out.println();
		System.out.println("No Of Beans:"+noOfBeans);
		
		String[] beans = context.getBeanDefinitionNames();
		System.out.println();
		Arrays.stream(beans).forEach(bean->System.out.println("----> "+bean));
	}

}
