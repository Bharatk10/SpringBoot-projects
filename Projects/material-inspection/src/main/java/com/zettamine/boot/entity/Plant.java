package com.zettamine.boot.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
	private String plantId;
	@Column(nullable = false)
	private String plantName;
	@Column(nullable = false)
	private String location;
	@Column(nullable = false)
	private String state;
	
	private Character status;
	
	@OneToMany(mappedBy="plant", cascade= CascadeType.ALL)
	private List<InspectionLot> inspectionLot = new ArrayList<>();
	

}
