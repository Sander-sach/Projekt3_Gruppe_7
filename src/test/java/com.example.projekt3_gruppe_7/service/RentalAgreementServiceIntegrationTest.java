package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.CarStatus;
import com.example.projekt3_gruppe_7.model.Location;
import com.example.projekt3_gruppe_7.model.RentalAgreement;
import com.example.projekt3_gruppe_7.model.SubscriptionType;
import com.example.projekt3_gruppe_7.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class RentalAgreementServiceIntegrationTest {

    @Autowired
    RentalAgreementService rentalAgreementService;

    @Autowired
    CarRepository carRepository;

    // Happy flow
    @Test
    void testCreateRentalAgreement_availableCar_returnsTrue() throws Exception {
        // Arrange - opret en ledig bil i databasen
        Car car = new Car();
        car.setStelnumber("TEST123456789");
        car.setMaker("Toyota");
        car.setModel("Yaris");
        car.setColor("Rød");
        car.setYear(2023);
        car.setStatus(CarStatus.AVAILABLE);
        carRepository.save(car);

        Car savedCar = carRepository.findAll().stream()
                .filter(c -> c.getStelnumber().equals("TEST123456789"))
                .findFirst()
                .orElseThrow();

        RentalAgreement agreement = new RentalAgreement(
                savedCar.getCarId(),
                1L,
                LocalDate.now(),
                LocalDate.now().plusMonths(5),
                Location.HQ,
                SubscriptionType.LIMITED );

        // Act
        boolean result = rentalAgreementService.create(agreement);

        // Assert
        assertTrue(result);
    }

    // Exception flow
    @Test
    void testCreateRentalAgreement_rentedCar_returnsFalse() throws Exception {
        // Arrange - opret en bil der allerede er udlejet
        Car car = new Car();
        car.setStelnumber("RENTED987654321");
        car.setMaker("Ford");
        car.setModel("Focus");
        car.setColor("Blå");
        car.setYear(2022);
        car.setStatus(CarStatus.RENTED);
        carRepository.save(car);

        Car savedCar = carRepository.findAll().stream()
                .filter(c -> c.getStelnumber().equals("RENTED987654321"))
                .findFirst()
                .orElseThrow();

        RentalAgreement agreement = new RentalAgreement(
                savedCar.getCarId(),
                1L,
                LocalDate.now(),
                LocalDate.now().plusMonths(5),
                Location.HQ,
                SubscriptionType.LIMITED );

        // Act
        boolean result = rentalAgreementService.create(agreement);

        // Assert
        assertFalse(result);
    }
}