package com.zettamine.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.accounts.entity.Account;

public interface AccountRepository extends JpaRepository<Account,Long>{

	Optional<Account> findByCustomerId(Long customerId);

}
