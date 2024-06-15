package com.zettamine.mpa.escrow.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EscrowNameDto {
		
	@JsonProperty(access = Access.WRITE_ONLY)
	private String escoName;
	
	@Valid
	private List<EscrowServiceAreaDto> serviceAreas;
	
	@Valid
	private List<EscrowAgentDto> escrowAgent;
}
