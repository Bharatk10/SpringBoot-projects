package com.zettamine.order.constants;

public interface ValidationConstants {
	
	public static final String INTEGER_PATTERN = "^[0-9 ]*$";
	
	public static final String INTEGER_PATTERN_ERROR = "Only Integers (0-9) are allowed for the field";
	
	public static final String PRICE_PATTERN = "^[0-9]+(\\.[0-9]{1,2})?$";
	
	public static final String PRICE_PATTERN_ERROR = "Only Integers (0-9) are allowed for the field";

	public static final String NAME_PATTERN = "^[a-zA-Z\\s]*$";
	
	public static final String NAME_PATTERN_ERROR = "Only Characters are allowed for the Product Name";
	
	

}
