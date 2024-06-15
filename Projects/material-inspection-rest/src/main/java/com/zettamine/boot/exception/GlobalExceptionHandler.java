package com.zettamine.boot.exception;

import java.util.HashMap;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
    	
    	HashMap<String,String> errors = new HashMap<>();
    	
    	  ex.getBindingResult().getFieldErrors().forEach(error -> {
    	        errors.put(error.getField(), error.getDefaultMessage());
    	    });
        return ResponseEntity.badRequest().body(errors);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handelDataInitigirityException(DataIntegrityViolationException ex){
    	
    	return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    	
    }
    
    @ExceptionHandler(DateViolationException.class)
    public ResponseEntity<?> handelDateViolationException(DateViolationException ex){
    	
    	return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    	
    }
    
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleException(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(ex.getMessage());
    }
    @ExceptionHandler(NoResourcesFoundException.class)
    public ResponseEntity<?> handlesException(NoResourcesFoundException ex) {
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(ex.getMessage());
    }
   
   
    
  
}
