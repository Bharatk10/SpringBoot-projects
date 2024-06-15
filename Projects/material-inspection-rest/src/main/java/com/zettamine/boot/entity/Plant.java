package com.zettamine.boot.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zettamine.boot.constants.ValidationConstants;
import com.zettamine.boot.models.ValidState;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "plant_name"))
public class Plant {

	@Id
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Pattern(regexp = ValidationConstants.PLANT_ID_PATTERN,message = ValidationConstants.PLANT_ID_ERROR)
	private String plantId;
	
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Column(nullable = false)
	@Pattern(regexp = ValidationConstants.NAME_PATTERN,message = ValidationConstants.NAME_ERROR)
	private String plantName;
	
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Column(nullable = false)
	@Pattern(regexp = ValidationConstants.LOCATION_PATTERN,message=ValidationConstants.LOCATION_ERROR)
	private String location;
	
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Column(nullable = false)
	@ValidState(message = ValidationConstants.STATE_ERROR)
	private String state;
	
	@JsonIgnore
	private Character status;
	
	@OneToMany(mappedBy="plant", cascade= CascadeType.ALL)
	@JsonIgnore
	private List<InspectionLot> inspectionLot = new ArrayList<>();
	

}
