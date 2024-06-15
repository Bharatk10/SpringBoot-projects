package com.zettamine.accounts.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.zettamine.accounts.dto.LoansDto;

@Component
public class LoansFallBack implements LoansFeignClient {

	@Override
	public ResponseEntity<LoansDto> fetchLoanDetails(String mobileNumber) {
		return null;
	}

}
