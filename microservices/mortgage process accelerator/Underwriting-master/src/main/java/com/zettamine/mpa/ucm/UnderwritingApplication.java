package com.zettamine.mpa.ucm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UnderwritingApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnderwritingApplication.class, args);
	}

}
