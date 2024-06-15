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
import com.zettamine.boot.cms.service.ContactService;
import com.zettamine.boot.cms.serviceimpl.ContactServiceImpl;

import jakarta.transaction.Transactional;

@SpringBootApplication
@Transactional
public class Application implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@Autowired
	private ContactService contactService;

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		
		List<Contact> contacts = contactService.getAllContacts();
		
//		contacts.forEach(System.out::println);

	}

}
