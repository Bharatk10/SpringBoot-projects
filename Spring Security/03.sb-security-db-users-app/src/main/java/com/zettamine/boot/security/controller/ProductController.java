package com.zettamine.boot.security.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.boot.repository.ProductRepository;
import com.zettamine.boot.security.entity.Product;



@RestController
public class ProductController {
	
	@GetMapping("/products")
	public ResponseEntity<?> getAllProducts(){
		
		List<Product> products = ProductRepository.getAllProducts();
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProduct(@PathVariable Integer id){
		
		Product product = ProductRepository.getAllProducts().stream()
				.filter(prod->prod.productId().equals(id)).findFirst().orElse(null);
		
	if(product == null) {
		return new ResponseEntity<String>("The product is not available with the id",HttpStatus.BAD_REQUEST);
	}	
		
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	@PostMapping("/product")
	public ResponseEntity<?> saveProduct(@RequestBody Product product){
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
}
