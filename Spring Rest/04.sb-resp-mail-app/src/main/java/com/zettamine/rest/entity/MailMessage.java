package com.zettamine.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailMessage {
	

	private String toMail;
	private String subject;
	private String body;

}
