package com.zettamine.boot.services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	private JavaMailSender emailSender;

	public EmailService(JavaMailSender emailSender) {
		super();
		this.emailSender = emailSender;
	}
	/*
	public boolean sendEmail(String toEmail, String subject, String msgBody) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setSubject(subject);
		msgBody = "<p style=color:red;text-align:center;font-size:22px>"+msgBody+"</p>";
		message.setText(msgBody);
		message.setTo(toEmail);
		
		try {
			emailSender.send(message);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	*/
	
	
	public boolean sendEmail(String toEmail, String subject, String msgBody) {
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
	
		msgBody = "<p style=color:red;text-align:center;font-size:22px>"+msgBody+"</p>";
		try {
			helper.setTo(toEmail);
			
			//mimeMessage.setContent(msgBody, "text/html");
			helper.setText(msgBody,true);
			
			helper.setSubject(subject);
			//helper.setFrom("SUNIL <sunil.edureka@gmail.com>");
			helper.setFrom("sunil.edureka@gmail.com", "SUNIL JOSEPH");
			emailSender.send(mimeMessage);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
