package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.CarRegistration;
import com.example.projekt3_gruppe_7.repository.CarRegistrationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        CarRegistration carRegistration = new CarRegistration("12345","12345","12345");

        //Act

    }
}
