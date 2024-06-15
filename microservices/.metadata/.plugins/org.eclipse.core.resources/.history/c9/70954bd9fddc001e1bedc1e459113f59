package com.zettamine.accounts.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.accounts.constants.AccountsConstants;
import com.zettamine.accounts.dto.CustomerDto;
import com.zettamine.accounts.dto.ErrorResponseDto;
import com.zettamine.accounts.dto.ResponseDto;
import com.zettamine.accounts.services.IAccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
@Tag(
        name = "CRUD REST APIs for Accounts in ZettaBank",
        description = "CRUD REST APIs in ZettaBank to CREATE, UPDATE, FETCH AND DELETE account details"
)
public class AccountsController {

	IAccountsService accService;
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
	@PostMapping(path = "/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
		accService.createAccount(customerDto);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
	}

	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAccountDetails(
			@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber) {
		CustomerDto customerDto = accService.fetchAccount(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(customerDto);
	}
	
	  @PutMapping("/update")
	    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid  @RequestBody CustomerDto customerDto) {
	        boolean isUpdated = accService.updateAccount(customerDto);
	        if(isUpdated) {
	            return ResponseEntity
	                    .status(HttpStatus.OK)
	                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
	        }else{
	            return ResponseEntity
	                    .status(HttpStatus.EXPECTATION_FAILED)
	                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
	        }
	    }
	  
	    @DeleteMapping("/delete")
	    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
	                                                                @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	                                                                String mobileNumber) {
	        boolean isDeleted = accService.deleteAccount(mobileNumber);
	        if(isDeleted) {
	            return ResponseEntity
	                    .status(HttpStatus.OK)
	                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
	        }else{
	            return ResponseEntity
	                    .status(HttpStatus.EXPECTATION_FAILED)
	                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
	        }
	    }	  
}
