package com.zettamine.boot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class Address {

	@Id
	@GeneratedValue
	private int addressId;
	private String street;
	private String state;
}
