package com.zettamine.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zettamine.accounts.dto.LoansDto;

@FeignClient("loans")
public interface LoansFeignClient {
	
	@GetMapping(path = "/api/fetch", consumes = "application/json")
	public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam String mobileNumber);

}
