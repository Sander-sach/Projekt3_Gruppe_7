package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.CarRegistration;
import com.example.projekt3_gruppe_7.repository.CarRegistrationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CarRegistrationServiceTest {

    @Mock
    CarRegistrationRepository carRegistrationRepository;

    @InjectMocks
    CarRegistrationService carRegistrationService;

    //Happy Flow
    @Test
    void testValidation_returnTrue(){
        //Arrange
        CarRegistration carRegistration = new CarRegistration(1L,"12345","12345","12345",1L);

        //Act hvis validation returner returner true gør complete() også
        boolean result = carRegistrationService.complete(carRegistration);

        //Assert
        assertTrue(result);
    }

    //Exception Flow
    @Test
    void testValidation_returnFalse(){
        //Arrange null værdi skal retunere falsh
        CarRegistration carRegistration = new CarRegistration(1L,null,"12345","12345",1L);

        //Act hvis validation returner returner true gør complete() også
        boolean result = carRegistrationService.complete(carRegistration);

        //Assert
        assertFalse(result);
    }
}
