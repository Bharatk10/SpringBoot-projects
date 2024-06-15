package com.zm.loans.repo;


import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zm.loans.entity.Loan;
import java.util.List;


public interface LoansRepo extends JpaRepository<Loan, Serializable>{
	
	boolean existsByMobileNum(String mobileNum);
	
	Optional<Loan>  findByMobileNum(String mobileNum);

}
