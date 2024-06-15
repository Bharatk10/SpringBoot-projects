package com.zettamine.accounts.service;

import com.zettamine.accounts.dto.CustomerDto;

public interface IAcountService {
	
	public void createAccount(CustomerDto customerDto);
	
	public CustomerDto getCustomerDetailsByMobileNumber(String mobileNumber);
	
	public CustomerDto updateCustomerDetails(CustomerDto customerDto );
	
	public void deleteCustomer(String mobileNumber);

}
