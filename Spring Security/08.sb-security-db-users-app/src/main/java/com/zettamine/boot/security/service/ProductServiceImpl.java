package com.zettamine.boot.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zettamine.boot.security.entity.Product;
import com.zettamine.boot.security.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService{
	
	private ProductRepository productRepo;

	@Override
	public List<Product> getAllProducts() {
		
		return productRepo.findAll();
	}

	@Override
	public Product getProductById(Integer id) {
		
		Optional<Product> optProduct = productRepo.findById(id);
		
		if(optProduct.isPresent()) {
			
			return optProduct.get();
		}
		return null;
	}

	@Override
	public Product saveProduct(Product product) {
		
		return productRepo.save(product);
	}

}
