package com.zettamine.mpa.lpm.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.zettamine.mpa.lpm.constants.PatternConstants;
import com.zettamine.mpa.lpm.constants.ValidationConstants;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(name = "Property Restriction Category Dto", description = "Schema representing a category of property restrictions, including details such as category type, description, and associated restrictions.")
@Data
public class PropertyRestrictionCategoryDto {
	
	@Schema(description = "Unique identifier of the property restriction category", example = "1")
	@JsonProperty(access = Access.READ_ONLY)
	private Integer categoryId;
	
	@Schema(description = "Type of the property restriction category (e.g., 'property type', 'Environmental Approval')", example = "ENVIRONMENT APPROVAL")
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = PatternConstants.STRING_PATTERN,message  = ValidationConstants.STRING_PATTERN_ERROR_MESSAGE)
	private String categoryType;
	
	@Schema(description = "Description of the property restriction category", example = "LACK OF APPROVALS FROM THE STATE IN ENVIRONMENTAL SENSITIVE ZONES WILL RESTRICT MORTGAGE LOAN CLEARANCES")
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = PatternConstants.STRING_PATTERN,message  = ValidationConstants.STRING_PATTERN_ERROR_MESSAGE)
	private String categoryDescription;
	
	@Schema(description = "List of property restrictions associated with this category")
	@JsonProperty(access = Access.READ_ONLY)
	private List<PropertyRestrictionDto> propertyRestrictions;

}
