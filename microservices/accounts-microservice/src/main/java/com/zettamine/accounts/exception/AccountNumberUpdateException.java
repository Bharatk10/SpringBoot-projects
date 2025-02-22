package com.zettamine.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AccountNumberUpdateException extends RuntimeException{
	
	public AccountNumberUpdateException(String message) {
		
		super(message);
	}

}
