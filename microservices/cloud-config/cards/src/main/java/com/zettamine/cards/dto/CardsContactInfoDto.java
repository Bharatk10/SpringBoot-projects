package com.zettamine.cards.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
@ConfigurationProperties(prefix = "cards")
@Data
public class CardsContactInfoDto{
	
		private	String message;
		private	Map<String, String> contactDetails;
		private	List<String> onCallSupport;

}