package com.zettamine.boot.entity;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DiselEngine implements Engine {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DiselEngine.class);

	public DiselEngine() {
		LOGGER.info("Disel Engine :: Bean");
	}

	@Override
	public Boolean startEngine() {
		LOGGER.info("DiselEngine starting...");
		return new Random().nextBoolean();
	}

}
