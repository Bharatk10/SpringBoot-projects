package com.zettamine.boot.cms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zettamine.boot.cms.entities.Contact;
import com.zettamine.boot.cms.repository.ContactRepository;
import com.zettamine.boot.cms.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepo;

	@Override
	public List<Contact> getContactsInAscByContactName() {

//		List<Contact> contacts = contactRepo.findByOrderByContactName();
//
//		return contacts;
		
		List<Contact> contacts = contactRepo.findAll(Sort.by("contactName"));
		
		return contacts;
	}

	@Override
	public List<Contact> getContactsDescByContactName() {

		List<Contact> contacts = contactRepo.findByOrderByContactNameDesc();

		return contacts;

	}

	@Override
	public List<Contact> getContactsInAscByContactNameAndContactNumber() {
		
		List<Contact> contacts = contactRepo.findByOrderByContactNameAscContactNumberAsc();
		
		return contacts;
	}

}
