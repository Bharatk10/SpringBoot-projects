package com.zettamine.boot.models;

import java.sql.Date;

import com.zettamine.boot.constants.ValidationConstants;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InspLotSearchCriteria {
	

	private Date fromDate;
	
	private Date toDate;
	private String materialId;
	private String plantId;
	private Integer vendorId;
	private String status;
}
