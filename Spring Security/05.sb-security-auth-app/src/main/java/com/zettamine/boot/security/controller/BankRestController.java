package com.zettamine.boot.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankRestController {
	
	@GetMapping("/home")
	public String getHomePage() {
		
		return "Welcome to home page";
	}
	@GetMapping("/admin")
	@PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
	public String getAdminPage() {
		
		return "Welcome to Admin page";
	}
	@GetMapping("/customer/balance")
	public String getBalancePage() {
		
		return "Customer balance Page";
	}

	@GetMapping("/care")
	public String getCustomerCarePage() {
		
		return "Customer care Page";
	}
	@GetMapping("/accounts")
	public String getAccountPage() {
		
		return "Accounts Page";
	}
	@GetMapping("/loans")
	public String getLoanPage() {
		
		return "Loans Page";
	}
	@GetMapping("/deposits")
	public String getDepositPage() {
		
		return "Deposits Page";
	}



}
