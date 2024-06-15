package com.zettamine.boot.cms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.zettamine.boot.cms.entities.Contact;
import com.zettamine.boot.cms.repository.ContactRepository;
import com.zettamine.boot.cms.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepo;

	@Override
	public List<Contact> getAllContacts() {
		
		long noOfContacts =contactRepo.count();
		 
		List<Contact> contacts = null;
		
		int pageSize = 3;
		
		int totalPages = (int) Math.ceil((float)(noOfContacts)/pageSize);
		
		for(int i = 0;i<totalPages;i++) {
			
			Page<Contact> page = contactRepo.findAll(PageRequest.of(i, pageSize));
			contacts = page.getContent();
			System.out.println("Page: "+(i+1)+" of "+totalPages);
			contacts.forEach(System.out::println);
		}
		
		return contacts;
	}


	

}
