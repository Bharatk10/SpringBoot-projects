package com.zettamine.boot.aop.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.boot.aop.entity.Product;
import com.zettamine.boot.aop.service.ProductService;

@RestController
public class ProductController {
	
	
	private ProductService prodService;

	public ProductController(ProductService prodService) {
		super();
		this.prodService = prodService;
	}
	
	@PostMapping(path = "/product/save")
	public Product saveProduct(@RequestParam("productId") Integer productId, @RequestParam("productName") String productName, @RequestParam("productPrice") Float productPrice) {
		return prodService.saveProduct(productId, productName, productPrice);
	}
	
	@DeleteMapping("/product/delete/{pid}")
	public String removeProduct(@PathVariable("pid") Integer productId) {
		prodService.deleteProductById(productId);
		return "Product Removed from Catalog";
	}

}
