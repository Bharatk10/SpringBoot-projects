package com.zettamine.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zettamine.boot.entity.Product;

@Controller
public class ProductController {
	
	
	@GetMapping("/product")
	public String gethomePage(Model model) {
		model.addAttribute("product", new Product());
		return "addProduct";
	}
	
	@PostMapping("/saveProduct")
    public String saveProduct(Product product,Model model ) {
		model.addAttribute("message", model);
        System.out.println(product);
        
        return "redirect:/product"; 
    }
}
