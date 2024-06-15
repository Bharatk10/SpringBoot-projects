package com.zettamine.boot.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.boot.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Serializable> {

}
