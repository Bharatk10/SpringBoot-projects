package com.zettamine.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CustomerDto {
	  
	private String name;
	private String email;
	private String mobileNumber;
	
	//@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private AccountDto accountDto;

}
