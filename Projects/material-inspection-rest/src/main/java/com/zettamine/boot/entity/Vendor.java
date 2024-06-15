package com.zettamine.boot.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zettamine.boot.constants.ValidationConstants;
import com.zettamine.boot.models.ValidState;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "vendor_name"),			
							@UniqueConstraint(columnNames = "vendor_email")
						})
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "vid_generator")
	@SequenceGenerator(name = "vid_generator",sequenceName = "vendor_sequence",initialValue = 5001,allocationSize = 1)
	//@Pattern(regexp = ValidationConstants.VENDOR_ID_PATTERN,message=ValidationConstants.VENDOR_ID_ERROR)
	private Integer vendorId;
	
	
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Column(nullable = false)
	@Pattern(regexp = ValidationConstants.NAME_PATTERN,message = ValidationConstants.NAME_ERROR)
	private String vendorName;
	
	
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@Column(nullable = false)
	@Pattern(regexp = ValidationConstants.EMAIL_PATTERN,message = ValidationConstants.EMAIL_ERROR)
	private String vendorEmail;
	
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
	
	@OneToMany(mappedBy="vendor", cascade= CascadeType.ALL)
	@JsonIgnore
	private List<InspectionLot> inspectionLot = new ArrayList<>();
}
