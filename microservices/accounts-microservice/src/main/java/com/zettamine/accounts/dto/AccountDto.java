package com.zettamine.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AccountDto {

	private Long accountNumber;

	private String accountType;

	private String branchAddress;

}
