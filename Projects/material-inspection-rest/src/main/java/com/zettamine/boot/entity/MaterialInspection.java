package com.zettamine.boot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zettamine.boot.constants.ValidationConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MAT_ISP_CH",uniqueConstraints = @UniqueConstraint(columnNames = {"material_id", "ch_Desc"}))
public class MaterialInspection {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "chid_generator")
	@SequenceGenerator(name = "chid_generator",sequenceName = "ch_id_sequence",initialValue = 101,allocationSize = 1)
	private Integer channelId;

	@Column(name = "ch_desc")
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	private String channelDescription;

	@ManyToOne
	@JoinColumn(name = "material_id", referencedColumnName = "materialId")
	@ToString.Exclude
	//@JsonBackReference
	@JsonIgnore
	private Material material;

	@Column(name = "tol_ul")
	@NotNull(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	@DecimalMin(value = "0.1", message = ValidationConstants.TOLLERANCE_VALUE_ERROR)
	//@Pattern(regexp  = ValidationConstants.TOLLERANCE_PATTERN,message = ValidationConstants.TOLLERANCE_ERROR)
	private Float upperTollerance;

	@Column(name = "tol_ll")
	@DecimalMin(value = "0.1", message = ValidationConstants.TOLLERANCE_VALUE_ERROR)
	//@Pattern(regexp  = ValidationConstants.TOLLERANCE_PATTERN,message = ValidationConstants.TOLLERANCE_ERROR)
	private Float lowerTollerance;

	@Column(name = "ums")
	@NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
	private String ums;
	
	@JsonIgnore
	private Character status;

}
