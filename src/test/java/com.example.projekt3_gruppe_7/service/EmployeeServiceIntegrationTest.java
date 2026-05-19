package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.Employee;
import com.example.projekt3_gruppe_7.model.EmployeeRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class EmployeeServiceIntegrationTest {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DataSource dataSource;


    //Happy flow
    @Test
    void testCreateEmployeeUser_validEmployee_returnsTrue(){
        //Arrange
        Employee employee = new Employee("Test User", EmployeeRole.ADMIN,"username","password1");

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
    // sletter gemt date efter test
    @AfterEach
    void cleanUp() throws SQLException {
        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.execute("DELETE FROM employee WHERE username IN ('username', 'sameusername')");
        }
    }

}
