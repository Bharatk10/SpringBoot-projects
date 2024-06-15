package com.zettamine.boot.cms;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.zettamine.boot.cms.entities.Contact;
import com.zettamine.boot.cms.repository.ContactRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	@Autowired
	private ContactRepository contactRepo;
	
	
	public static void main(String[] args) {
		 SpringApplication.run(Application.class, args);
		
	}


	@Override
	public void run(String... args) throws Exception {
		
		//to get contact based on contactId
		/*
		Optional<Contact> optContact = contactRepo.findById(101);
		
		if(optContact.isPresent()) {
			
			Contact contact = optContact.get();
			
			LOGGER.info("Find by id method:"+contact);
		}*/
		
		//to get contacts based on multiple contactIds
		
		/*
		 * Iterable<Integer> ids = Arrays.asList(101,102,103,109,108);
		 * 
		 * List<Contact> contacts = contactRepo.findAllById(ids);
		 * 
		 * contacts.forEach(System.out::println);
		 */
		
	// to delete contcats based on multiple ids
		
	/*
	 * Iterable<Integer> ids = Arrays.asList(102,103,109,108);
	 * 
	 * contactRepo.deleteAllById(ids);
	 */
		
		 List<Contact> contacts = contactRepo.findAll();
		 
		 LOGGER.info("All contacts");
		 
		 //contacts.stream().forEach(e->LOGGER.info(e.toString()));
		 contacts.forEach(System.out::println);
		 	
	}

}
