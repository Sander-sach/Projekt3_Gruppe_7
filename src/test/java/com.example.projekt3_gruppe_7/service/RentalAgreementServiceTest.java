package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.*;
import com.example.projekt3_gruppe_7.repository.CarRepository;
import com.example.projekt3_gruppe_7.repository.RentalAgreementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RentalAgreementServiceTest {
    
    @Mock
    RentalAgreementRepository rentalAgreementRepository;

    @Mock
    CarRepository carRepository;

    @InjectMocks
    RentalAgreementService rentalAgreementService;

    // Happy flow
    @Test
    void testCreateRentalAgreement_returnTrue(){

        Car availableCar = new Car();
        availableCar.setCarId(1L);
        availableCar.setStatus(CarStatus.AVAILABLE);

        when(carRepository.findById(1L)).thenReturn(availableCar);
        RentalAgreement agreement = new RentalAgreement(
                1L,
                2L,
                LocalDate.now(),
                LocalDate.now().plusMonths(5),
                Location.HQ,
                SubscriptionType.LIMITED, 1200
        );
        // Act
        boolean result = rentalAgreementService.create(agreement);

        //Assert
        assertTrue(result);
    }
    // Exception flow
    @Test
    void testCreateRentalAgreement_returnFalse() {

        Car rentedCar = new Car();
        rentedCar.setCarId(1L);
        rentedCar.setStatus(CarStatus.RENTED);

        when(carRepository.findById(1L)).thenReturn(rentedCar);

        RentalAgreement agreement = new RentalAgreement(
                1L,
                2L,
                LocalDate.now(),
                LocalDate.now().plusMonths(5),
                Location.HQ,
                SubscriptionType.LIMITED, 1200
        );

        // Act
        boolean result = rentalAgreementService.create(agreement);

        // Assert
        assertFalse(result);
    }
}