package com.zm.loans.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.zm.loans.dto.LoansDto;
import com.zm.loans.entity.Loan;
import com.zm.loans.exception.ResourceNotFoundException;
import com.zm.loans.mapper.LoanMapper;
import com.zm.loans.repo.LoansRepo;
import com.zm.loans.service.LoansService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements LoansService{
	
	private LoansRepo loansRepo;

	@Override
	public void createLoan(String mobileNum) {
		Loan loan = new Loan();
		
		loan.setAmountPaid(0);
		
		String loanNum = 1010201+new Random().nextInt(90000)+"";
		
		loan.setLoanNum(loanNum);
		loan.setMobileNum(mobileNum);
		loan.setLoanType("HOME LOAN");
		loan.setTotalLoan(100000);
		loan.setOutstandingAmount(100000);
		
		loansRepo.save(loan);
		
		
	}

	@Override
	public LoansDto getLoan(String mobileNum) {
		
		 Loan loan = loansRepo.findByMobileNum(mobileNum).orElseThrow(()-> new ResourceNotFoundException("Loan", "mobile number", mobileNum));
		
		
		return LoanMapper.mapToLoanDto(new LoansDto(),loan);
	}

	@Override
	public void updateLoan(@Valid LoansDto loansDto) {


		Loan loan = loansRepo.findByMobileNum(loansDto.getMobileNum()).orElseThrow(()-> new ResourceNotFoundException("Loan", "mobile number", loansDto.getMobileNum()));
		
	      Loan updatedLoan =	LoanMapper.mapToLoan(new Loan(), loansDto);
		
	      updatedLoan.setLoanId(loan.getLoanId());
	      
	      loansRepo.save(updatedLoan);
	      
	}

	@Override
	public void deleteLoan(String mobileNum) {
		
		Loan loan = loansRepo.findByMobileNum(mobileNum).orElseThrow(()-> new ResourceNotFoundException("Loan", "mobile number", mobileNum));
		
		loansRepo.delete(loan);
		
	}

}
