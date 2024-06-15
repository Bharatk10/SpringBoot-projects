package com.zm.loans.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Loan extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer loanId;
	private String mobileNum;
	private String loanNum;
	private String loanType;
	private Integer totalLoan;
	private Integer amountPaid;
	private Integer outstandingAmount;
	 

}
