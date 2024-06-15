package com.zettamine.accounts.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.zettamine.accounts.constants.AccountsConstants;
import com.zettamine.accounts.dto.AccountsDto;
import com.zettamine.accounts.dto.CustomerDto;
import com.zettamine.accounts.entity.Accounts;
import com.zettamine.accounts.entity.Customer;
import com.zettamine.accounts.exceptions.CustomerAlreadyExistsException;
import com.zettamine.accounts.exceptions.ResourceNotFoundException;
import com.zettamine.accounts.mapper.AccountsMapper;
import com.zettamine.accounts.mapper.CustomerMapper;
import com.zettamine.accounts.repository.AccountsRepository;
import com.zettamine.accounts.repository.CustomerRepository;
import com.zettamine.accounts.services.IAccountsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
	private CustomerRepository customerRepo;
	private AccountsRepository accountsRepo;
	
	@Override
	public void createAccount(CustomerDto customerDto) {		
		Optional<Customer> optCustomer = customerRepo.findByMobileNumber(customerDto.getMobileNumber());
		
		if(optCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber " +customerDto.getMobileNumber()); 
		}
		
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		//customer.setCreatedAt(LocalDateTime.now());
		//customer.setCreatedBy("Anonymous");
		Customer savedCustomer = customerRepo.save(customer);
		
		Accounts newAcc = createNewAccount(savedCustomer);
		//newAcc.setCreatedAt(LocalDateTime.now());
		//newAcc.setCreatedBy("Anonymous");
		accountsRepo.save(newAcc);
	}

	/**
	 * @param customer - Customer Object
	 * @return the new account details
	 */
	private Accounts createNewAccount(Customer customer) {
		Accounts newAccount = new Accounts();
		newAccount.setCustomerId(customer.getCustomerId());
		long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

		newAccount.setAccountNumber(randomAccNumber);
		newAccount.setAccountType(AccountsConstants.SAVINGS);
		newAccount.setBranchAddress(AccountsConstants.ADDRESS);
		return newAccount;
	}
    /**
     * @param mobileNumber - Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */
	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		Customer customer = customerRepo.findByMobileNumber(mobileNumber)
							.orElseThrow(() -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber));
		
		Accounts accounts = accountsRepo.findByCustomerId(customer.getCustomerId())
							.orElseThrow(() -> new ResourceNotFoundException("Account", "Customer ID", customer.getCustomerId()+""));
		
		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        
		AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accounts, new AccountsDto());
		
		customerDto.setAccountsDto(accountsDto);
		
        return customerDto;
	}

    /**
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */	
	@Override
	public boolean updateAccount(CustomerDto customerDto) {
	       boolean isUpdated = false;
	        AccountsDto accountsDto = customerDto.getAccountsDto();
	        if(accountsDto !=null ){
	            Accounts accounts = accountsRepo.findById(accountsDto.getAccountNumber()).orElseThrow(
	                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
	            );
	            AccountsMapper.mapToAccounts(accountsDto, accounts);
	            accounts = accountsRepo.save(accounts);

	            Long customerId = accounts.getCustomerId();
	            Customer customer = customerRepo.findById(customerId).orElseThrow(
	                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
	            );
	            CustomerMapper.mapToCustomer(customerDto,customer);
	            customerRepo.save(customer);
	            isUpdated = true;
	        }
	        return  isUpdated;
	}

	/**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */	
	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepo.deleteByCustomerId(customer.getCustomerId());
        customerRepo.deleteById(customer.getCustomerId());
        return true;
	}
	
}
