package com.zm.loans.mapper;

import com.zm.loans.dto.LoansDto;
import com.zm.loans.entity.Loan;

public class LoanMapper {
	
	
	public static Loan mapToLoan(Loan loan,LoansDto loansDto)
	{
		loan.setAmountPaid(loansDto.getAmountPaid());
		loan.setLoanNum(loansDto.getLoanNum());
		loan.setLoanType(loansDto.getLoanType());
		loan.setMobileNum(loansDto.getMobileNum());
		loan.setOutstandingAmount(loansDto.getOutstandingAmount());
		loan.setTotalLoan(loansDto.getTotalLoan());
		
		
		return loan;
		
	}

	public static LoansDto mapToLoanDto(LoansDto loansDto, Loan loan) {
		
		loansDto.setAmountPaid(loan.getAmountPaid());
		loansDto.setLoanNum(loan.getLoanNum());
		loansDto.setLoanType(loan.getLoanType());
		loansDto.setMobileNum(loan.getMobileNum());
		loansDto.setOutstandingAmount(loan.getOutstandingAmount());
		loansDto.setTotalLoan(loan.getTotalLoan());
		
		
		return loansDto;
	}

}
