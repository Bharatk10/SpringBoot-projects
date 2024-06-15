package com.zettamine.rest.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.zettamine.rest.entity.MailMessage;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	
	
	private JavaMailSender emailSender;

	public MailService(JavaMailSender emailSender) {
		super();
		this.emailSender = emailSender;
	}
	
	public boolean sendEmail(MailMessage mail) {
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		
		mail.setBody("<p style=color:red;text-align:center;font-size:22px>"+mail.getBody()+"</p>");
	 
		try {
			helper.setTo(mail.getToMail());
			
			
			helper.setText(mail.getBody(),true);
			
			helper.setSubject(mail.getSubject());
			
			helper.setFrom("bharatkumar84101@gmail.com", "Bhart Kumar");
			emailSender.send(mimeMessage);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

}
