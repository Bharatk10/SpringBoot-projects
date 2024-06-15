package com.zettamine.mpa.lpm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.zettamine.mpa.lpm.constants.PatternConstants;
import com.zettamine.mpa.lpm.constants.ValidationConstants;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(name = "Property Restriction Dto", description = "Schema representing a property restriction, including details such as the restriction type, category type, and description.")
@Data
public class PropertyRestrictionDto {

	@JsonProperty(access = Access.READ_ONLY)
	private Integer restrictionId;

	@Schema(description = "Type of the category the restriction belongs to (e.g., 'property type', 'Environmental Approval')", example = "PROPERTY TYPE")
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = PatternConstants.STRING_PATTERN, message = ValidationConstants.STRING_PATTERN_ERROR_MESSAGE)
	private String categoryType;

	@Schema(description = "Type of the property restriction which related to specific categorytype (e.g., 'Multi-unit', 'HOA')", example = "MULTI-UNIT") 
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = PatternConstants.STRING_PATTERN, message = ValidationConstants.STRING_PATTERN_ERROR_MESSAGE)
	private String restrictionType;

	@Schema(description = "Description of the property restriction", example = "Multi-unit residential properties like Duplex, Triplex, Four Plex etc are restricted for mortgaging")
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = PatternConstants.STRING_PATTERN, message = ValidationConstants.STRING_PATTERN_ERROR_MESSAGE)
	private String restrictionDescription;

}