package com.zettamine.mpa.lpm.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Schema(name = "Loan Product Search Criteria Dto", description = "Schema representing search criteria for filtering loan products.")
@Data
public class LoanProductSearchCriteria {
	
	@Schema(description = "Loan term in months to filter loan products", example = "360")
	private Integer loanTerm;
	
	
	@Min(value = 300, message = "Credit score must be at least 300.")
    @Max(value = 900, message = "Credit score must be at most 900.")
	private Integer creditScore;
	
	private Boolean privateMortgageInsurance;
	
	private List<String> categoryTypes;
	
	private List<String> restrictionTypes;
	
	private List<String> escrowRequirements;
	
	private List<String> criterias;
	
	private Boolean prepayPenality;
	
	private Boolean status;
	
	
}
