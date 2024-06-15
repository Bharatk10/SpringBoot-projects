package com.zettamine.boot.entity;

import java.util.ArrayList;
import java.util.List;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "vendor_name"),
		                     @UniqueConstraint(columnNames = "vendor_email") })
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vid_generator")
	@SequenceGenerator(name = "vid_generator", sequenceName = "vendor_sequence", initialValue = 5001, allocationSize = 1)
	private Integer vendorId;
	@Column(nullable = false)
	private String vendorName;
	@Column(nullable = false)
	private String vendorEmail;
	@Column(nullable = false)
	private String location;
	@Column(nullable = false)
	private String state;

	private Character status;

	@OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
	private List<InspectionLot> inspectionLot = new ArrayList<>();
}
