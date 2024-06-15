package com.zettamine.boot.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn(value = { "wheels","diselEngine", "petrolEngine" })
public class Car {

	private static final Logger LOGGER = LoggerFactory.getLogger(Car.class);

	private Engine engine;

	private Wheels wheels;

	public Car() {
		LOGGER.info("Car :: Bean");
	}

	@Autowired
	public Car(@Qualifier Engine engine, Wheels wheels) {

		this();

		this.engine = engine;
		this.wheels = wheels;
	}
	

	public void start() {
		LOGGER.info("----\tCar starting\t----");
		
		Boolean tyreStatus = wheels.getTyreStatus();
		
		if(tyreStatus) {
			LOGGER.info("Tyres are in perfect condition");
			Boolean status = engine.startEngine();
			if (status) {
				LOGGER.info("---# Happy Journey #----");
			} else {
				LOGGER.error("---! Journey Cancelled !----");
			} 
		}
		else {
			LOGGER.info("Tyre is punchered");
			LOGGER.error("---! Journey Cancelled !----");
		}
		
	}

}
