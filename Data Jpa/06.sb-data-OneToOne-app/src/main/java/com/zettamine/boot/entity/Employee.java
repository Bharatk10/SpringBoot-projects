package com.zettamine.boot.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Employee {
	
	@Id
	@GeneratedValue
	private int empId;
	private String empName;
	private float salary;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	

}
