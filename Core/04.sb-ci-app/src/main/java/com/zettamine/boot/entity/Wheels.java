package com.zettamine.boot.entity;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Wheels {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Wheels.class);


	public Wheels() {
		LOGGER.info("Wheels :: Bean");
	}
	
	public Boolean getTyreStatus() {
		Boolean status = new Random().nextBoolean();
		
		return status;
	}

}
