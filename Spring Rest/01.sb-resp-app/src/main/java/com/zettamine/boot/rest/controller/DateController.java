package com.zettamine.boot.rest.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personal")
public class DateController {

	@GetMapping("/date")
	public  ResponseEntity<String> getDate() {
		
		 LocalDateTime ldt = LocalDateTime.now();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
		    String date = ldt.format(formatter);
		    
		    HttpHeaders header = new HttpHeaders();
		    
		    header.add("name", "Zettamine");

		    
		    ResponseEntity<String> respEntity = new ResponseEntity<String>(date,header,HttpStatus.OK);
		    
		    return respEntity;
		
	}
	@GetMapping(path={"/age/{name}/{year}","/age","/age/{name}","/age/{year}"})
	public  ResponseEntity<String> checkInsuranceApplication(@PathVariable("name") Optional<String> name,@PathVariable("year") Optional<Integer> year) {
		
		 LocalDateTime ldt = LocalDateTime.now();
		   
		int currentYear =  ldt.getYear();
		
		String response = "";
		
		if(name.isEmpty() || year.isEmpty() ) {
			
			response = String.format("Please provide the required parameters");
		}
		else{
			if(year.get() >= currentYear) {
				
				response = String.format("Hi "+name.get()+" you are not eligible for Insurance");
			}
			else {
				int age = currentYear - year.get();
				response = String.format("Hi "+name.get()+" you are "+age+" years of age and eligible for Insurance");
			}
		}
	
		    ResponseEntity<String> respEntity = new ResponseEntity<String>(response,HttpStatus.OK);
		    
		    return respEntity;
		
	}

}
