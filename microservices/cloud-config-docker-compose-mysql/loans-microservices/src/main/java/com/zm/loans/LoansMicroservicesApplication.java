package com.zm.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class LoansMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansMicroservicesApplication.class, args);
	
	}

}
