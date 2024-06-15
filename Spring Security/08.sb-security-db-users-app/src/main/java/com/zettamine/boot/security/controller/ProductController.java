package com.zettamine.boot.security.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.boot.security.entity.Product;
import com.zettamine.boot.security.service.IProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("zetta")
@AllArgsConstructor
public class ProductController {
	
	private IProductService productService;
	
	@GetMapping("/api/products")
	private ResponseEntity<List<Product>> getAllProducts() {
		
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@GetMapping("/api/product/{pId}")
	private ResponseEntity<?> getProductById(@PathVariable Integer pId){
		
		Product product = productService.getProductById(pId);
		
		if(product == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("The entered product Id is not present product Id:"+pId);
		}
		
		return ResponseEntity.ok(product);
	}
	@PostMapping("/api/product")
	private ResponseEntity<String> saveProduct(@RequestBody Product product){
		
		 productService.saveProduct(product);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("The Product is saved");
	}

}
