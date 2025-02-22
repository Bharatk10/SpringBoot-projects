package com.zettamine.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String resourceName,String fieldName,String value) {
		
		super(String.format("The %s details are not available for %s : %s",resourceName,fieldName,value));
	}

}
