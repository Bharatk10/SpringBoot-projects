package com.zettamine.accounts.service;

import com.zettamine.accounts.dto.CustomerDetailsDto;

public interface ICustomerDetailsService {
	
	CustomerDetailsDto fetchCustomerDetails(String mobileNumber);

}
