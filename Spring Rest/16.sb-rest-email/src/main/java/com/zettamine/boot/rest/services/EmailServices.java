package com.zettamine.boot.rest.services;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.activation.URLDataSource;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServices {
	private JavaMailSender mailSender;

	public EmailServices(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}
	
	public boolean sendEmail(String toEmail) {
		String contents = getEmailContents("Sunil", "Joseph", "sunilozhakal@hotmail.com", "134566#");
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

			URLDataSource source = new URLDataSource(this.getClass().getResource("/images/logo.png"));
			helper.addInline("CompanyLogo", source);
			
			mailSender.send(message);
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return false;
	}

	private String getEmailContents(String fname, String lname, String email, String pass) {
		InputStream fis = this.getClass().getClassLoader().getResourceAsStream("email-templates/new-user-mail.html");
		String str=null;
		try {
			str = IOUtils.toString(fis, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String data = String.format(str, fname, lname, email, pass);
		
		return data;
	}
}
