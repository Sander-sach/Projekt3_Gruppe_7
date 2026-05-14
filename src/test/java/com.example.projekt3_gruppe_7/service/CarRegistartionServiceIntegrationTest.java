package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.*;
import com.example.projekt3_gruppe_7.repository.*;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class CarRegistartionServiceIntegrationTest {

    @Autowired
    CarRegistrationService carRegistrationService;
    @Autowired
    RentalAgreementRepository rentalAgreementRepository;
    @Autowired
    CarRegistrationRepository carRegistrationRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    CustomerRepository customerRepository;


    //Happy flow
    @Test
    void test_findAgreementsWithoutRegistration_returnNotEmpty(){
        //Arrange
        Long carId;

        Customer customer = new Customer("Test Kunde", "12345678", "test@test.dk");
        Car car = new Car("TEST-UNIQUE001","blue","volvo", "cruiser",1999,CarStatus.AWAITING_REGISTRATION);
        customerRepository.save(customer);
        carRepository.save(car);

        car = carRepository.findByStelNumber("TEST-UNIQUE001");
        carId = car.getCarId();
        customer = customerRepository.findByPhone("12345678");

        RentalAgreement rentalAgreement = new RentalAgreement(carId,1L, LocalDate.of(2026, 5, 14), LocalDate.of(2026, 10, 14), Location.HQ,SubscriptionType.UNLIMITED,1200);

        rentalAgreementRepository.save(rentalAgreement);

        //Act
        List<RentalAgreement> result = carRegistrationService.findAgreementsWithoutRegistration();

        //Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    //Exception flow
    @Test
    void test_findAgreementsWithoutRegistration_returnEmpty(){
        //Arrange
        Customer customer = new Customer("Test Kunde", "test@test.dk", "12345678");
        Car car = new Car("TEST-UNIQUE002","blue","volvo", "cruiser",1999,CarStatus.AWAITING_REGISTRATION);
        customerRepository.save(customer);
        carRepository.save(car);

        RentalAgreement rentalAgreement = new RentalAgreement(1L,1L, LocalDate.of(2026, 5, 14),LocalDate.of(2026, 10, 14), Location.HQ,SubscriptionType.UNLIMITED,1200);
        CarRegistration carRegistration = new CarRegistration("123456","12345","12345",1L);


        rentalAgreementRepository.save(rentalAgreement);
        carRegistrationRepository.save(carRegistration);

        //Act
        List<RentalAgreement> result = carRegistrationService.findAgreementsWithoutRegistration();


        //Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Autowired
    private DataSource dataSource;

    @AfterEach
    void cleanUp() throws SQLException {
        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.execute("DELETE FROM car_registration");
            stmt.execute("DELETE FROM rental_agreement");
            stmt.execute("DELETE FROM customer");
            stmt.execute("DELETE FROM car WHERE stel_number LIKE 'TEST%'");
        }
    }

}
