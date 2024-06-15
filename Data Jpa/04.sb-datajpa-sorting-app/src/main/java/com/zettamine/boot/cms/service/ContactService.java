package com.zettamine.boot.cms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zettamine.boot.cms.entities.Contact;


public interface ContactService {
	
	List<Contact> getContactsInAscByContactName();
	
	List<Contact> getContactsDescByContactName();
	
	List<Contact> getContactsInAscByContactNameAndContactNumber();

}
