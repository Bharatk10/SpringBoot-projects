package com.zettamine.boot.models;

import java.sql.Date;

import com.zettamine.boot.constants.ValidationConstants;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class InspLotModel {
	
	
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = ValidationConstants.NAME_PATTERN,message = ValidationConstants.NAME_ERROR)
    private String vendorName;
    
    @NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = ValidationConstants.NAME_PATTERN,message = ValidationConstants.NAME_ERROR)
    private String plantName;
    
    @NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
    private String materialName;
    
    @NotNull(message = ValidationConstants.BLANK_ERROR_MESSAGE)
    private Boolean startDate;
    
 
   
}
