package com.zettamine.mpa.lpm.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Prepay Penality Dto", description = "Schema representing prepayment penalty information for a loan product. This includes details such as the product ID, minimum penalty amount, and penalty percentage.")
@Data
public class PrePayPenalityDto {
	
	@Schema(description = "Unique identifier of the loan product", example = "10001")
	private Integer productId;
	
	@Schema(description = "Minimum penalty amount for prepayment of a loan product", example = "1000.00")
	private Float minPenalityAmount;
	
	@Schema(description = "Percentage of the loan amount charged as a penalty for prepayment", example = "2.5")
	private Float penalityPercentage;

}
