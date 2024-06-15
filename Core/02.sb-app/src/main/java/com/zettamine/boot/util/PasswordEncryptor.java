package com.zettamine.boot.util;

import java.util.Base64;
import java.util.Base64.Encoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordEncryptor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordEncryptor.class);
	
	public PasswordEncryptor() {
	
		LOGGER.info("Passwordencryptor :: bean");
	}

	public  static String getEncryptedPassword(String password) {
		
		Encoder encoder = Base64.getEncoder();
		
		String encrypted = encoder.encodeToString(password.getBytes());
		
		return encrypted;
		
	}

}
