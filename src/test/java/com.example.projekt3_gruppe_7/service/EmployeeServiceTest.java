package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.Employee;
import com.example.projekt3_gruppe_7.model.EmployeeRole;
import com.example.projekt3_gruppe_7.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;
    // Spy er brugt da BCrypt altid vil retunere false med Mock
    @Spy
    BCryptPasswordEncoder passwordEncoder;


    @InjectMocks
    EmployeeService employeeService;

    //Happy flow
    @Test
    void testValidPassword_returnNotNull(){
        //Arrange
        String username = "username";
        String plainPassword = "password1";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(plainPassword);

        Employee employee = new Employee("Test",EmployeeRole.ADMIN,username,hashedPassword);

        when(employeeRepository.findByUsername(username)).thenReturn(employee);


        //Act
        Employee result = employeeService.login(username, plainPassword);


        //Assert
        assertNotNull(result);
    }

    //Exception flow
    @Test
    void testInvalidPassword_returnNull(){
        //Arrange
        //Test password too short
        String username = "username";
        String plainPassword = "short1";
        Employee employee = new Employee("Test",EmployeeRole.ADMIN,username,"hashedPassword");

        when(employeeRepository.findByUsername(username)).thenReturn(employee);

        //Act
        Employee result = employeeService.login(username,plainPassword);

        //Assert
        assertNull(result);
    }

}
