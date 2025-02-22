package com.zettamine.mpa.ucm.service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "loan-product-service", url = "http://localhost:9010/api/v1/loan-product/product")
public interface LoanProductFeignClient {

	@GetMapping("/fetch/loan-product-id/{prodName}")
	public ResponseEntity<?> getLoanProductIdByName(@PathVariable("prodName") String loanProductName);

	@PutMapping("/update/loan-status/{productId}")
	public ResponseEntity<Boolean> updateLoanProdStatus(@PathVariable("productId") Integer prodId);
}
