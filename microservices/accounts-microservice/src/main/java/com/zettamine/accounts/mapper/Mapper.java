package com.zettamine.accounts.mapper;

import com.zettamine.accounts.dto.AccountDto;
import com.zettamine.accounts.dto.CustomerDto;
import com.zettamine.accounts.entity.Account;
import com.zettamine.accounts.entity.Customer;

public class Mapper {
	
	public static Customer mapCustomerDtoToCustomer(CustomerDto customerDto,Customer customer) {
		
	
		customer.setName(customerDto.getName());
		customer.setEmail(customerDto.getEmail());
		customer.setMobileNumber(customerDto.getMobileNumber());
	
		return customer;
	}
	
	public static CustomerDto mapCustomerToCustomerDto(Customer customer,CustomerDto customerDto) {
		
		customerDto.setName(customer.getName());
		customerDto.setMobileNumber(customer.getMobileNumber());
		customerDto.setEmail(customer.getEmail());
		
		return customerDto;
	}
	
	public static AccountDto mapAccountToAccountDto(Account account,AccountDto accountDto) {
		
		accountDto.setAccountNumber(account.getAccountNumber());
		accountDto.setAccountType(account.getAccountType());
		accountDto.setBranchAddress(account.getBrachAddress());
		
		return accountDto;
	}
	public static Account mapAccountDtoToAccount(AccountDto accountDto,Account account) {
		
		account.setAccountType(accountDto.getAccountType());
		account.setBrachAddress(accountDto.getBranchAddress());
		
		return account;
	}

}
