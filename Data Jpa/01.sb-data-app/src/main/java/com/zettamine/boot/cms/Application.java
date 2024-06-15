package com.zettamine.boot.cms;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zettamine.boot.cms.entities.Contact;
import com.zettamine.boot.cms.repository.ContactRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		
		ContactRepository contactRepo = context.getBean(ContactRepository.class);
		/*
		Contact contact = new Contact(101,"Bharat","8919261807");
		
		contactRepo.save(contact);*/
		
		Contact contact2 = new Contact(105,"Bharat","8919267807");
		Contact contact3 = new Contact(106,"Pavani","8919268912");
		Contact contact4 = new Contact(107,"Priya","7919261009");
		Contact contact5 = new Contact(108,"hema","7891926890");
		Contact contact6 = new Contact(109,"Kumari","8919267237");
		Contact contact7 = new Contact(110,"Pramila","8239278912");
		Contact contact8 = new Contact(111,"Jagadesh","7099261009");
		Contact contact9 = new Contact(112,"suraj","7891786890");
		Contact contact10 = new Contact(113,"priyank","7819261009");
		Contact contact11 = new Contact(114,"jaya","9091926890");
		
		
		List<Contact> contacts = Arrays.asList(contact2,contact3,contact4,contact5,contact6,contact7,contact8,contact9,contact10,contact11);
		
		contactRepo.saveAll(contacts);
		
		
		
		List<Contact> contact = contactRepo.findAll();
		 
		 contact.stream().forEach(System.out::println);
//		 
		//contactRepo.deleteById(104);
		
//		List<Contact> contacts = contactRepo.findAll();
//		 contacts.stream().forEach(System.out::println);
		 
		
		context.close();
	}

}
