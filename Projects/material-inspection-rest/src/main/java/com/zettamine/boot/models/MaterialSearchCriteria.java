package com.zettamine.boot.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialSearchCriteria {
	
	private String materialId;
	private String desc;
	private String materialType;

}
