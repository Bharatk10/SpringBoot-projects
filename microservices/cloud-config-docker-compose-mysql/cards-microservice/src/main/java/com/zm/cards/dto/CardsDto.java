package com.zm.cards.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardsDto {
	
	@NotBlank
	@Pattern(regexp = "[6-9]{1}[0-9]{9}")
	private String mobileNum;
	@NotBlank
	private String cardNum;
	private String cardType;
	private Integer totalLimit;
	private Integer amountUsed;
	private Integer availableAmount;
}
