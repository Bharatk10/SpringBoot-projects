package com.zm.loans.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoansDto {
	
	@NotBlank
	@Pattern(regexp =  "[6-9]{1}[0-9]{9}")
	private String mobileNum;
	@NotBlank
	private String loanNum;
	@NotBlank
	private String loanType;
	@NotNull
	private Integer totalLoan;
	@NotNull
	private Integer amountPaid;
	@NotNull
	private Integer outstandingAmount;

}
