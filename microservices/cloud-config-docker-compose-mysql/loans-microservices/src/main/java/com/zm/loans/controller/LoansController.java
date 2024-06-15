package com.zm.loans.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zm.loans.dto.LoansDto;
import com.zm.loans.service.LoansService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/loan")
@AllArgsConstructor
public class LoansController {
	

	private LoansService loansService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createLoan(@RequestParam String mobileNum)
	{
		
		loansService.createLoan(mobileNum);
		

		return ResponseEntity.ok().body("loan create suscessfully");	
				
	}
	@GetMapping("/fetch")
	
	public ResponseEntity<?> getLoan(@RequestParam String mobileNum)
	{
		
		
		return ResponseEntity.ok().body(loansService.getLoan(mobileNum));
		
	}
	@PutMapping("/update")
	public ResponseEntity<?> updateLoan(@RequestBody @Valid LoansDto loansDto)
	{
		
		loansService.updateLoan(loansDto);
		
		return ResponseEntity.ok().body("Loan updated successfully");
		
	}
	
	@DeleteMapping("/delete")
	
	public ResponseEntity<?> deletLoan(@RequestParam String mobileNum)
	{
		
		loansService.deleteLoan(mobileNum);
		
		return ResponseEntity.ok().body("Loan deleted successfully");
		
	}
	
	
	

}
