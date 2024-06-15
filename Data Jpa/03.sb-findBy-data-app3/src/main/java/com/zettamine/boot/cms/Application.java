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

import jakarta.transaction.Transactional;

@SpringBootApplication
@Transactional
public class Application implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@Autowired
	private ContactRepository contactRepo;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		List<Contact> contacts = contactRepo.findAll();

		LOGGER.info("All contacts");

		contacts.forEach(cont -> LOGGER.info(cont.toString()));

		Contact contact = contactRepo.findByContactName("Ravi");

		LOGGER.info(contact.toString());

		contact = contactRepo.findByContactNumber("7891926180");

		LOGGER.info(contact.toString());

		contact = contactRepo.findByContactNameAndContactNumber("sneha", "7891926180");

		LOGGER.info(contact.toString());

		contacts = contactRepo.findByContactNameOrContactNumber("Bharat","7891926180");

		LOGGER.info("All contacts");

		contacts.forEach(cont -> LOGGER.info(cont.toString()));
		

		// contactRepo.deleteByContactName("sneha");
		
		List<Contact> evenId = contactRepo.findEvenId();
		
		System.out.println(evenId);
		
		System.out.println(contactRepo.count());
	}

}
