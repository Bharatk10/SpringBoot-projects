package com.zettamine.order.entity;

import java.util.List;

import com.zettamine.order.model.LineItem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "orderid_generator")
	@SequenceGenerator(name = "orderid_generator",sequenceName = "order_id_sequence",initialValue = 1001,allocationSize = 1)
	private Integer orderId;
	
	private List<LineItem> items;
}
