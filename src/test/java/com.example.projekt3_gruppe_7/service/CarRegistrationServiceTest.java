package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.CarRegistration;
import com.example.projekt3_gruppe_7.model.CarStatus;
import com.example.projekt3_gruppe_7.repository.CarRegistrationRepository;
import com.example.projekt3_gruppe_7.repository.CarRepository;
import com.example.projekt3_gruppe_7.repository.RentalAgreementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarRegistrationServiceTest {

    @Mock
    CarRegistrationRepository carRegistrationRepository;
    @Mock
    CarRepository carRepository;
    @Mock
    RentalAgreementRepository rentalAgreementRepository;

    @InjectMocks
    CarRegistrationService carRegistrationService;

    //Happy Flow
    @Test
    void testValidation_returnTrue(){
        //Arrange
        CarRegistration carRegistration = new CarRegistration("LEASE-001","IRK-001","AB12345",1L);
        Car mockCar = new Car("TEST001", "Golf", "VW", "Sort", 2022, CarStatus.AWAITING_REGISTRATION);

        // mock retunere ved defult null på updateCarStatus() when() løser
        when(carRepository.findByRentalAgreementId(1L)).thenReturn(mockCar);


        //Act hvis validation returner returner true gør complete() også
        boolean result = carRegistrationService.complete(carRegistration);

        //Assert
        assertTrue(result);
    }

    //Exception Flow
    @Test
    void testValidation_returnFalse(){
        //Arrange null værdi skal retunere falsh
        CarRegistration carRegistration = new CarRegistration(null,"IRK-001","AB12345",1L);

        //Act hvis validation returner returner true gør complete() også
        boolean result = carRegistrationService.complete(carRegistration);

        //Assert
        assertFalse(result);
    }
}
