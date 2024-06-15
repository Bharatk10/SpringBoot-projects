package com.zettamine.boot.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.boot.cms.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
