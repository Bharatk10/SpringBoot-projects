package com.zettamine.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.accounts.constants.AppConstants;
import com.zettamine.accounts.dto.CustomerDto;
import com.zettamine.accounts.dto.ErrorResponseDto;
import com.zettamine.accounts.dto.ResponseDto;
import com.zettamine.accounts.service.IAcountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path ="/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Tag(
        name = "CRUD REST APIs for Accounts in ZettaBank",
        description = "CRUD REST APIs in ZettaBank to CREATE, UPDATE, FETCH AND DELETE account details"
)
public class AccountController {
	
	private IAcountService accountService;
	@Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer &  Account inside ZettaBank"
    )

	@ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
		
		accountService.createAccount(customerDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AppConstants.STATUS_201, AppConstants.MESSAGE_201));
		
	}
	
	@GetMapping("/fetch/{mobileNumber}")
	public ResponseEntity<CustomerDto> fetchCustomerDetails(@PathVariable String mobileNumber){
		
		CustomerDto customerDto = accountService.getCustomerDetailsByMobileNumber(mobileNumber);
		
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateCustomerDetails(@RequestBody CustomerDto customerDto){
		
		accountService.updateCustomerDetails(customerDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AppConstants.STATUS_200, AppConstants.MESSAGE_200));
		
	}
	
	@DeleteMapping("/delete/{mobileNumber}")
	public ResponseEntity<ResponseDto> deleteCustomerDetails(@PathVariable String mobileNumber){
		
		accountService.deleteCustomer(mobileNumber);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AppConstants.STATUS_200, AppConstants.MESSAGE_200));
		
	}

}

