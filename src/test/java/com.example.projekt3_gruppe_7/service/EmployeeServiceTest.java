package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    EmployeeService employeeService;

    //Happy flow
    @Test
    void testValidPassword_returnTrue(){
        //Arrange
        String password = "password1";

        //Act
        boolean result = employeeService.validatePasswordCharacters(password);

        //Assert
        assertTrue(result);
    }

    //Exception flow
    @Test
    void testInvalidPassword_returnFalse(){
        //Arrange
        //Test password too short
        String password = "short1";

        //Act
        boolean result = employeeService.validatePasswordCharacters(password);

        //Assert
        assertFalse(result);
    }

}
