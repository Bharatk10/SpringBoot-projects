package com.zettamine.accounts.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "accounts")
@Data
public class AccountsContactInfoDto{
	
		private	String message;
		private	Map<String, String> contactDetails;
		private	List<String> onCallSupport;

}
