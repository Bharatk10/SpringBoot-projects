package com.zettamine.boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServices {
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	public boolean sendEmail(String toEmail) {
		Context context = new Context();
		context.setVariable("fname", "Sunil");
		context.setVariable("lname", "Joseph");
		
		String contents = templateEngine.process("email", context); 
		
		String subject = "Welcome to Zettamine";
		String from = "SUNIL <sunil.edureka@gmail.com>";
		String to = toEmail;
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setSubject(subject);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setText(contents, true);

			mailSender.send(message);
			return true;
		} catch (Exception ex) {
		}
		return false;
	}
}


