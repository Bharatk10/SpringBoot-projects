package com.zettamine.boot.repository;

import java.util.ArrayList;
import java.util.List;
import com.zettamine.boot.entity.Product;


public class ProductRepository {
	
	public static List<Product> getAllProducts(){
		
		List<Product> products = new ArrayList<>(List.of(new Product(101,"Phone"),
			    new Product(102, "Laptop"))); 
		
		return products;
		
	}
	
	

}
