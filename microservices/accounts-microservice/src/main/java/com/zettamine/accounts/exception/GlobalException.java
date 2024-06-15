package com.zettamine.accounts.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.zettamine.accounts.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(
			CustomerAlreadyExistsException exception, WebRequest webRequest) {
		ErrorResponseDto responseDto = new ErrorResponseDto();

		String apiPath = webRequest.getDescription(false);
		responseDto.setApiPath(apiPath);
		responseDto.setErrorCode(HttpStatus.BAD_REQUEST);
		responseDto.setErrorMessage(exception.getMessage());
		responseDto.setErrorTime(LocalDateTime.now());
		return new ResponseEntity<ErrorResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
			ResourceNotFoundException exception, WebRequest webRequest) {
		ErrorResponseDto responseDto = new ErrorResponseDto();

		String apiPath = webRequest.getDescription(false);
		responseDto.setApiPath(apiPath);
		responseDto.setErrorCode(HttpStatus.NOT_FOUND);
		responseDto.setErrorMessage(exception.getMessage());
		responseDto.setErrorTime(LocalDateTime.now());
		return new ResponseEntity<ErrorResponseDto>(responseDto,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(AccountNumberUpdateException.class)
	public ResponseEntity<ErrorResponseDto> handleAccountNumberUpdateException(
			AccountNumberUpdateException exception, WebRequest webRequest) {
		ErrorResponseDto responseDto = new ErrorResponseDto();

		String apiPath = webRequest.getDescription(false);
		responseDto.setApiPath(apiPath);
		responseDto.setErrorCode(HttpStatus.BAD_REQUEST);
		responseDto.setErrorMessage(exception.getMessage());
		responseDto.setErrorTime(LocalDateTime.now());
		return new ResponseEntity<ErrorResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
	}

}
