package com.zettamine.boot.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class Employee {

	private static final Logger LOGGER = LoggerFactory.getLogger(Employee.class);

	private int eno;

	private String ename;

	public void setEno(int eno) {
		this.eno = eno;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	@Override
	public String toString() {
		return "Employee [eno=" + eno + ", ename=" + ename + "]";
	}

	public Employee() {

		LOGGER.info(System.lineSeparator());
		LOGGER.info("Employee :: Bean");
	}

}
