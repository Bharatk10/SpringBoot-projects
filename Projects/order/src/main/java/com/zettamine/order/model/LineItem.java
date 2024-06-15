package com.zettamine.order.model;

import com.zettamine.order.constants.AppConstants;
import com.zettamine.order.constants.ValidationConstants;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LineItem {
	
	@NotBlank(message = AppConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = ValidationConstants.INTEGER_PATTERN,message = ValidationConstants.INTEGER_PATTERN_ERROR)
	private Integer itemid;
	
	@Pattern(regexp = ValidationConstants.INTEGER_PATTERN,message = ValidationConstants.INTEGER_PATTERN_ERROR)
	@NotBlank(message = AppConstants.BLANK_ERROR_MESSAGE)
	private Integer productId;
	
	@NotBlank(message = AppConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = ValidationConstants.NAME_PATTERN,message=ValidationConstants.NAME_PATTERN_ERROR)
	private String productName;
	
	@NotBlank(message = AppConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = ValidationConstants.INTEGER_PATTERN,message = ValidationConstants.INTEGER_PATTERN_ERROR)
	private Integer quantity;
	
	@NotBlank(message = AppConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = ValidationConstants.PRICE_PATTERN,message = ValidationConstants.INTEGER_PATTERN_ERROR)
	private Double price;
	

}
