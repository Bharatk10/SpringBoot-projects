package com.zettamine.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account extends BaseEntity {
	
	@Id
	private Long accountNumber;
	
	private Long customerId;
	
	private String accountType;
	
	private String brachAddress;
	

}
