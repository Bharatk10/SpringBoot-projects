package com.zm.cards.exception.handler;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.zm.cards.dto.ErrorResponseDto;
import com.zm.cards.exception.ResourceNotFoundException;


@RestControllerAdvice
public class CardsGlobalExceptionHandler extends ResponseEntityExceptionHandler{

	

		
		
		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatusCode status, WebRequest request) {
			
			
			Map<String, String> error = new HashMap<>();
			
			ex.getBindingResult().getAllErrors().forEach(
					
					err->{
						error.put(((FieldError)err).getField(),err.getDefaultMessage());
					});
			
			return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		}

	

		//@ExceptionHandler({CustomerAlreadyExistsExcetpion.class})
		public ResponseEntity<ErrorResponseDto> customerAlreadyExists(
				//CustomerAlreadyExistsExcetpion ex
				WebRequest webRequest)
		
		{
			
			ErrorResponseDto errorResponseDto = new ErrorResponseDto();
			
			String apiPath = webRequest.getDescription(false);
			
			errorResponseDto.setApiPath(apiPath.substring(apiPath.indexOf("=")+1));
			errorResponseDto.setErrorCode(HttpStatus.BAD_REQUEST);
			//errorResponseDto.setErrorMessage(ex.getLocalizedMessage());
			errorResponseDto.setErrorTime(LocalDateTime.now());
			
			return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
			
		}
		
		@ExceptionHandler({ResourceNotFoundException.class})
		public ResponseEntity<ErrorResponseDto> resourceNotFound(ResourceNotFoundException ex,WebRequest webRequest)
		
		{
			
			ErrorResponseDto errorResponseDto = new ErrorResponseDto();
			
			String apiPath = webRequest.getDescription(false);
			
			errorResponseDto.setApiPath(apiPath.substring(apiPath.indexOf("=")+1));
			errorResponseDto.setErrorCode(HttpStatus.BAD_REQUEST);
			errorResponseDto.setErrorMessage(ex.getLocalizedMessage());
			errorResponseDto.setErrorTime(LocalDateTime.now());
			
			return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
			
		}
		@ExceptionHandler({Exception.class})
		public ResponseEntity<?> unhandledException(Exception exception,WebRequest webRequest)
		{ 
			return new ResponseEntity<>(new ErrorResponseDto(webRequest.getDescription(false),
															HttpStatus.INTERNAL_SERVER_ERROR, 
															"something went wrong", 
															 LocalDateTime.now())
			
					
					,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

	

	
}
