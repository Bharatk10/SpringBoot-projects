package com.zettamine.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
	@NotEmpty(message = "AccountNumber can not be a null or empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "AccountNumber must be 10 digits")
	@JsonProperty(access = Access.READ_ONLY)
	private Long accountNumber;
	
	@JsonProperty(access = Access.READ_ONLY)
	@NotEmpty(message = "AccountType can not be a null or empty")
	private String accountType;
	
	@JsonProperty(access = Access.READ_ONLY)
	@NotEmpty(message = "BranchAddress can not be a null or empty")
	private String branchAddress;
}
