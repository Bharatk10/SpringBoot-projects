package com.zettamine.boot.entity;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class PetrolEngine implements Engine {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PetrolEngine.class);

	public PetrolEngine() {
		LOGGER.info("Petrol Engine :: Bean");
	}

	@Override
	public Boolean startEngine() {
		LOGGER.info("PetrolEngine starting...");
		return new Random().nextBoolean();
	}
	
	

}
