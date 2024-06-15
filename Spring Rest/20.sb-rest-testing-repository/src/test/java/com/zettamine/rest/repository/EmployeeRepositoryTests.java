package com.zettamine.rest.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.zettamine.rest.entity.Employee;
import com.zettamine.rest.repository.EmployeeRepository;

@DataJpaTest
public class EmployeeRepositoryTests {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;


    @BeforeEach
    public void setupTestData(){
        employee = Employee.builder()
                .firstName("SUNIL")
                .lastName("JOSEPH")
                .email("sunil@gmail.com")
                .build();
    }
    
    // JUnit Test for save employee operation
    @Test
    @DisplayName("JUnit test for save employee operation")
    public void givenEmployeeObject_whenSave_thenReturnSaveEmployee(){
        Employee saveEmployee = employeeRepository.save(employee);
        System.out.println(saveEmployee);
        assertThat(saveEmployee).isNotNull();
        assertThat(saveEmployee.getId()).isGreaterThan(0);
    }
    
    // JUnit test for get Employee List operation
    @Test
    @DisplayName("JUnit test for get Employee List")
    public void givenEmployeeList_whenFindAll_thenEmployeeList(){
        Employee employeeOne = Employee.builder()
                .firstName("Pankaj")
                .lastName("Kumar")
                .email("pankaj@gmail.com")             
                .build();

        Employee employeeTwo = Employee.builder()
                .firstName("Praveen")
                .lastName("Kumar")
                .email("praveen@gmail.com")              
                .build();

        employeeRepository.save(employeeOne);
        employeeRepository.save(employeeTwo);

        // When : Action of behavious that we are going to test
        List<Employee> employees = employeeRepository.findAll();

        // Then : Verify the output
        assertThat(employees).isNotEmpty();
        assertThat(employees.size()).isEqualTo(2);
    }

    // JUnit test for get Employee By Id operation    
    @Test
    @DisplayName("JUnit test for get Employee By Id")
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject()
    {
        employeeRepository.save(employee);

        // When : Action of behavious that we are going to test
        Employee getEmployee = employeeRepository.findById(employee.getId()).get();

        // Then : Verify the output
        assertThat(getEmployee).isNotNull();
    }

    // JUnit test for get employee by email operation
    @Test
    @DisplayName("JUnit test for get employee by email operation")
    public void givenEmployeeEmail_whenFindByEmail_thenEmployeeObject() {

        employeeRepository.save(employee);

        // When: Action or behavior that we are going to test
        Employee getEmployee = employeeRepository.findByEmail("sunil@gmail.com").get();

        // Then: Verify the output or expected result
        assertThat(getEmployee).isNotNull();
        assertThat(getEmployee.getEmail()).isEqualTo("sunil@gmail.com");
    }

    // JUnit test for get employee update operation
    @Test
    @DisplayName("JUnit test for get employee update operation")
    public void givenEmployeeObject_whenUpdate_thenEmployeeObject() {
        employeeRepository.save(employee);

        Employee getEmployee = employeeRepository.findById(employee.getId()).get();

        getEmployee.setFirstName("SUNIL UPDATE");
        getEmployee.setLastName("Last Name");
        getEmployee.setEmail("update@gmail.com");

        Employee updatedEmployee = employeeRepository.save(getEmployee);

        // Then: Verify the output or expected result
        assertThat(updatedEmployee).isNotNull();
        assertThat(updatedEmployee.getEmail()).isEqualTo("update@gmail.com");
    }


    // JUnit test for delete employee operation
    @Test
    @DisplayName("JUnit test for delete employee operation")
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {
        employeeRepository.save(employee);
        // When: Action or behavior that we are going to test
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> deleteEmployee = employeeRepository.findById(employee.getId());
        // Then: Verify the output or expected result
        assertThat(deleteEmployee).isEmpty();
    }

    // JUnit test for custom query using JPQL with index param 
    @Test
    @DisplayName("JUnit test for custom query using JPQL with index")
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject() {
        employeeRepository.save(employee);

        // When: Action or behavior that we are going to test
        Employee getEmployee =   employeeRepository.findByJPQL(employee.getFirstName(), employee.getLastName());

        // Then: Verify the output or expected result
        assertThat(getEmployee).isNotNull();
    }

    // JUnit test for custom query using JPQL with name param 
    @Test
    @DisplayName("JUnit test for custom query using JPQL with name param ")
    public void givenFirstNameAndLastName_whenFindByJPQLNameParams_thenReturnEmployeeObject() {
        employeeRepository.save(employee);
        
        Employee getEmployee =   employeeRepository.findByJPQLNameParams(employee.getFirstName(), employee.getLastName());
        
        assertThat(getEmployee).isNotNull();
    }


    // JUnit test for custom query using findByNativeSQL with name index 
    @Test
    @DisplayName("JUnit test for custom query using findByNativeSQL with name param  ")
    public void givenFirstNameAndLastName_whenFindByfindByNativeSQL_thenReturnEmployeeObject() {
        employeeRepository.save(employee);
        Employee getEmployee =   employeeRepository.findByJPQLNameParams(employee.getFirstName(), employee.getLastName());
        assertThat(getEmployee).isNotNull();
    }


    // JUnit test for custom query using findByNativeSQLNameParam with name param 
    @Test
    @DisplayName("JUnit test for custom query using findByNativeSQLNameParam with name param  ")
    public void givenFirstNameAndLastName_whenfindByNativeSQLNameParam_thenReturnEmployeeObject() {
        employeeRepository.save(employee);

        // When: Action or behavior that we are going to test
        Employee getEmployee =   employeeRepository.findByJPQLNameParams(employee.getFirstName(), employee.getLastName());
        
        // Then: Verify the output or expected result
        assertThat(getEmployee).isNotNull();
    }
    
}



