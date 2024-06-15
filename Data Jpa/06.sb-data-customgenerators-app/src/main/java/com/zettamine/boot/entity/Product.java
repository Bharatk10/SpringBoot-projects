package com.zettamine.boot.entity;

import org.hibernate.generator.Generator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {

	@Id
	//@GeneratedValue()
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pid_generator")
//	@SequenceGenerator(name = "pid_generator",sequenceName = "prod_sequence",initialValue = 101,allocationSize = 1)
	
	private int productId;
	private String productName;
	private float productPrice;
	
	
	public Product(String productName, float productPrice) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
	}
	
	
	
	
}
