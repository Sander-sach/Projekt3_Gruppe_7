package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.CarStatus;
import com.example.projekt3_gruppe_7.model.Location;
import com.example.projekt3_gruppe_7.model.RentalAgreement;
import com.example.projekt3_gruppe_7.model.SubscriptionType;
import com.example.projekt3_gruppe_7.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class RentalAgreementServiceIntegrationTest {

    @Autowired
    RentalAgreementService rentalAgreementService;

    @Autowired
    DataSource dataSource;

    // Happy flow
    @Test
    void testCreateRentalAgreement_returnsTrue() {
        // Arrange - opret en ledig bil i databasen
        RentalAgreement agreement = new RentalAgreement(
                5L, 3L,
                LocalDate.now(),
                LocalDate.now().plusMonths(5),
                Location.HQ,
                SubscriptionType.LIMITED,
                3500.00);

        // Act
        boolean result = rentalAgreementService.create(agreement);

        // Assert
        assertTrue(result);
    }

    // Exception flow
    @Test
    void testCreateRentalAgreement_rentedCar_returnsFalse() {
        // Arrange - opret en bil der allerede er udlejet
        RentalAgreement agreement = new RentalAgreement(
                1L, 1L,
                LocalDate.now(),
                LocalDate.now().plusMonths(5),
                Location.HQ,
                SubscriptionType.LIMITED,
                3500.00 );

        // Act
        boolean result = rentalAgreementService.create(agreement);

        // Assert
        assertFalse(result);
    }
    @AfterEach
    void cleanUp() throws SQLException {
        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.execute("DELETE FROM rental_agreement WHERE car_id = 5 AND customer_id = 3");
            stmt.execute("UPDATE car SET status = 'AVAILABLE' WHERE car_id = 5");
        }
    }
}