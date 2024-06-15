package com.zm.loans.service;

import com.zm.loans.dto.LoansDto;

import jakarta.validation.Valid;

public interface LoansService {

	void createLoan(@Valid String mobileNum);

	LoansDto getLoan(String mobileNum);

	void updateLoan(@Valid LoansDto loansDto);

	void deleteLoan(String mobileNum);

}
