package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.Employee;
import com.example.projekt3_gruppe_7.model.EmployeeRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class EmployeeServiceIntegrationTest {

    @Autowired
    EmployeeService employeeService;

    //Happy flow
    @Test
    void testCreateEmployeeUser_validEmployee_returnsTrue(){
        //Arrange
        Employee employee = new Employee("Test User", EmployeeRole.ADMIN,"testuser123","password1");

        //Act
        boolean result = employeeService.createEmployeeUser(employee);

        //Assert
        assertTrue(result);
    }

    @Test
    void testCreateEmployeeUser_duplicateUsername_returnsFalse(){
        //Arrange
        Employee first = new Employee("Test User",EmployeeRole.ADMIN,"sameusername","password1");
        Employee second = new Employee("Test User2",EmployeeRole.ADMIN,"sameusername","password2");

        //Act
        employeeService.createEmployeeUser(first);
        boolean result = employeeService.createEmployeeUser(second);

        //Assert
        assertFalse(result);
    }
}
