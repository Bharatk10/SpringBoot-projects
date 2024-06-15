package com.zettamine.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zettamine.boot.entity.Address;
import com.zettamine.boot.entity.Employee;
import com.zettamine.boot.repository.EmployeeRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private EmployeeRepository empRepo;

	public Application(EmployeeRepository empRepo) {
		super();
		this.empRepo = empRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Address address = new Address();
		
		address.setStreet("B.C.Road");
		address.setState("Andhra Pradesh");
		
		Employee employee = new Employee();
		
		employee.setEmpName("Bharat");
		employee.setSalary(1098.23f);
		employee.setAddress(address);
		
		empRepo.save(employee);

		
		

	}

}
