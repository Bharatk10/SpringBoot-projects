package com.zettamine.boot.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.generator.Generator;

import com.zettamine.boot.generator.ProductIdGenerator;

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
	@GeneratedValue(generator = "pid_generataor")
	@GenericGenerator(name="pid_generataor",strategy = "com.zettamine.boot.generator.ProductIdGenerator")
	private String productId;
	
	
	private String productName;
	private float productPrice;
	
	
	public Product(String productName, float productPrice) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
	}
	
	
	
	
}
