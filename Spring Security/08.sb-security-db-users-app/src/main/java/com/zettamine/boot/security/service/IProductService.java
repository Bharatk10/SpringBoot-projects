package com.zettamine.boot.security.service;

import java.util.List;

import com.zettamine.boot.security.entity.Product;

public interface IProductService {
	
	List<Product> getAllProducts();
	
	Product getProductById(Integer id);
	
	Product saveProduct(Product product);

}
