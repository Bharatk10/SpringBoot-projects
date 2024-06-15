package com.zettamine.mpa.lpm.model;

import java.util.List;

import com.zettamine.mpa.lpm.constants.PatternConstants;
import com.zettamine.mpa.lpm.constants.ValidationConstants;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Schema(name = "Loan Product Dto",description = "Schema to hold information about a loan product. This includes details such as the product name, interest rate, loan term, maximum loan amount, and other loan-related attributes.")
@Data
public class LoanProductDto {

	@Schema(description = "This field represents the name of the loan product. It is a required field and should be a non-blank string", example = "CONVENTIONAL 30-YEAR FIXED")
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	private String productName;
	 
	@Schema(description = "This field represents the interest rate of the loan product. It is a required field and should be a non-blank string that matches a specified pattern (e.g., a decimal number).", example = "3.45")
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = PatternConstants.INTEREST_PATTERN,message  = ValidationConstants.INTREST_PATTERN_ERROR_MESSAGE)
	private String interestRate;
	
	@Schema(description = "This field represents the period of the loan product in months. It is a required field and should be a non-blank string that matches an integer pattern (e.g., a positive integer). ", example = "360")
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = PatternConstants.INTEGER_PATTERN,message = ValidationConstants.INTEGER_PATTERN_ERROR_MESSAGE)
	private String loanTerm;
	
	@Schema(description = "This field represents the maximum loan amount for the loan product. It is a required field and should be a non-blank string that matches a specified pattern (e.g., a numeric value)", example = "500000")
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = PatternConstants.MAX_LOAN_AMOUNT_PATTERN,message = ValidationConstants.MAX_LOAN_AMOUNT_PATTERN_ERROR_MESSAGE)
	private String maxLoanAmount;
	
	@Schema(description = "This field represents the minimum down payment required for the loan product. It is a required field and should be a non-blank string that matches a specified pattern", example = "25000")
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = PatternConstants.MIN_DOWN_PAY_PATTERN,message =ValidationConstants.MIN_DOWN_PAYEMNT_ERROR_MESSAGE)
	private String minDownPayment;
	
	@Schema(description = "This field specifies the type of the minimum down payment (e.g., percentage, absolute). It is a required field and should be a non-blank string that matches a specified pattern", example = "ABSOLUTE")
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = PatternConstants.STR_PATTERN,message = ValidationConstants.STR_PATTERN_ERROR_MESSAGE)
	private String minDownPaymentType;
	
	@Schema(description = "This field represents the minimum credit score required for the loan product. It is a required field and should be a non-blank string that matches a specified pattern (e.g., an integer value within a certain range of 300 to 900)", example = "620")
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = PatternConstants.CREDIT_RANGE_PATTERN,message = ValidationConstants.CREDIT_RANGE_PATTERN_ERROR_MESSAGE)
	private String minCreditScore;
	
	@Schema(description = "This field represents the maximum loan-to-value ratio for the loan product. It is a required field and should be a non-blank string that matches a specified pattern (e.g., an integer value within a certain range of 80 to 100)", example = "90")
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = PatternConstants.LTV_RANGE_PATTERN,message =ValidationConstants.LTV_RANGE_PATTERN_ERROR_MESSAGE)
	private String maxLoanToValueRatio;
	
	@Schema(description = "This field indicates whether private mortgage insurance is required for the loan product. It is a required field and should be a non-null Boolean value (true or false)", example = "true")
	@NotNull(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	private Boolean privateMortgageInsuranceReq;
	
	@Schema(description = "This field represents the origination fee for the loan product. It should be a string that matches a specified pattern ", example = "1200.34")
	@Pattern(regexp = PatternConstants.AMOUNT_PATTERN,message =ValidationConstants.AMOUNT_PATTERN_ERROR_MESSAGE)
	private String orginationFee;
	
	@Schema(description = "This field represents the lock-in period of the loan product in months. It is a required field and should be a non-blank string that matches an integer pattern (e.g., a positive integer)", example = "60")
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = PatternConstants.INTEGER_PATTERN,message = ValidationConstants.INTEGER_PATTERN_ERROR_MESSAGE)
	private String lockinPeriod;
	
	@Schema(description = " This field represents the minimum penalty amount for the loan product. It should be a string that matches a specified pattern", example = "1000.22")
	@Pattern(regexp = PatternConstants.AMOUNT_PATTERN,message =ValidationConstants.AMOUNT_PATTERN_ERROR_MESSAGE)
	private String minPenalityAmount;
	
	@Schema(description = "This field represents the penalty percentage for the loan product. It should be a string that matches a specified pattern", example = "5")
	@Pattern(regexp = PatternConstants.INTEREST_PATTERN,message  = ValidationConstants.INTREST_PATTERN_ERROR_MESSAGE)
	private String penalityPercentage;
	
	@Schema(description = "This field represents the document requirements for the loan product. It is a required field and should be a non-blank string describing the documents needed", example = "Proof of military service, income verification")
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	private String documentRequirement;
	
	@Schema(description = "This field represents a list of escrow requirements for the loan product. Each item in the list should be a string that matches a specified pattern", example = "[\"Property taxes\", \"Homeowners insurance\"]")
	private List<@Pattern(regexp = PatternConstants.STRING_PATTERN,
			message = ValidationConstants.STRING_PATTERN_ERROR_MESSAGE)
	        String> escrowRequirements;
	
	@Schema(description = "This field represents a list of property restriction types for the loan product. Each item in the list should be a string that matches a specified pattern", example = "[\"MULTI-UNIT\",\"PRE-EXISTING MORTGAGE LOAN\",\"FLOOD PLAINS\"]")
	private List<@Pattern(regexp = PatternConstants.STRING_PATTERN,
			message = ValidationConstants.STRING_PATTERN_ERROR_MESSAGE) 
			String> propertyRestrictionTypes;
	
	@Schema(description = "his field represents a list of underwriting criteria for the loan product. Each item in the list should be a string that matches a specified pattern", example = "[\"CREDIT SCORE REQUIREMENT\",\"DEBT-TO-INCOME RATIO\",\"LOAN-TO-VALUE RATIO\"]")
	private List<@Pattern(regexp = PatternConstants.STRING_PATTERN,
			message = ValidationConstants.STRING_PATTERN_ERROR_MESSAGE)
			String> underWritingCriteria;
	
}
