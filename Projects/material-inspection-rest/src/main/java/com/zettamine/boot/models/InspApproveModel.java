package com.zettamine.boot.models;

import com.zettamine.boot.constants.ValidationConstants;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class InspApproveModel {
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	private String Result;

	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	private String Remarks;

}
