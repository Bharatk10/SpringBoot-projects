package com.zettamine.boot.models;




import com.fasterxml.jackson.annotation.JsonProperty;
import com.zettamine.boot.constants.ValidationConstants;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class InspActualModel {
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer lotId;
	
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = ValidationConstants.CHANNEL_NAME_PATTERN,message = ValidationConstants.CHANNEL_NAME_ERROR)
	private String ChannelDesc;
	
	 @JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Float upperTollerance;
	
	@NotNull(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@DecimalMin(value = "0.1", message = ValidationConstants.TOLLERANCE_VALUE_ERROR)
	private Float maxMeasurement;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Float lowerTollerance;
	
	@NotNull(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@DecimalMin(value = "0.1", message = ValidationConstants.TOLLERANCE_VALUE_ERROR)
	private Float minMeasurement;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String ums;
}
