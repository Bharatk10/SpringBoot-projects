package com.zettamine.boot.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrsfController {

	@GetMapping("/csrf")
	public CsrfToken csrf(CsrfToken token) {
		
		return token;
	} 

}
