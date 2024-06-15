package com.zettamine.boot.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zettamine.boot.cms.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

	List<Contact> findByOrderByContactName();

	List<Contact> findByOrderByContactNameDesc();

	List<Contact> findByOrderByContactNameAscContactNumberAsc();

	

}
