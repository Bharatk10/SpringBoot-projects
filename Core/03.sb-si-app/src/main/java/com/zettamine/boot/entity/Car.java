package com.zettamine.boot.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;


@Component
@DependsOn(value = {"diselEngine","petrolEngine"})
public class Car {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Car.class);
	
	private Engine engine;

	public Car() {
		LOGGER.info("Car :: Bean");
	}
	
	@Autowired
	public void setEngine(Engine engine) {
		this.engine = engine;
	} 
	
	public void start() {
		LOGGER.info("----\tCar starting\t----");
		Boolean status = engine.startEngine();
		if(status) {
			LOGGER.info("---# Happy Journey #----");
		}
		else {
			LOGGER.error("---! Journey Cancelled !----");
		}
	}
	
	
	
	

}
