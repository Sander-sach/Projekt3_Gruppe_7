package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.*;
import com.example.projekt3_gruppe_7.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class CarRegistartionServiceIntegrationTest {

    @Autowired
    CarRegistrationService carRegistrationService;


    //Happy flow
    @Test
    void test_findAgreementsWithoutRegistration_returnNotEmpty(){
        //Arrange


        //Act
        List<RentalAgreement> result = carRegistrationService.findAgreementsWithoutRegistration();

        //Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    //Exception flow
    @Disabled("kræver tom database — konflikter med DML demo data")
    @Test
    void test_findAgreementsWithoutRegistration_returnEmpty(){
        //Arrange


        //Act
        List<RentalAgreement> result = carRegistrationService.findAgreementsWithoutRegistration();


        //Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void test_complete_missingFields_returnsFalse() {
        // Arrange
        CarRegistration form = new CarRegistration(null, "IRK001", "AB12345", 3L);

        // Act
        boolean result = carRegistrationService.complete(form);

        // Assert
        assertFalse(result);
    }

}
