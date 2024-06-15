package com.zettamine.boot;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zettamine.boot.entity.Product;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private ProductRepository productRepo;

	public Application(ProductRepository productRepo) {
		super();
		this.productRepo = productRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<Product> products = Arrays.asList(new Product("Phone",34900.34f),
											   new Product("Laptop",7600.00f),
											   new Product("Pen",10.00f));
		
		productRepo.saveAll(products);
		
		

	}

}
