package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.Customer;
import com.example.projekt3_gruppe_7.model.RentalAgreement;
import com.example.projekt3_gruppe_7.repository.BaseRepository;
import com.example.projekt3_gruppe_7.repository.RentalAgreementRepository;
import com.example.projekt3_gruppe_7.repository.RentalAgreementRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalAgreeementService {

    private final RentalAgreementRepository rentalagreementRepository;
    private final BaseRepository<Customer> customerRepository;
    private final BaseRepository<Car> carRepository;

    @Autowired
    public RentalAgreeementService(RentalAgreementRepository rentalagreementRepository, BaseRepository<Customer> customerRepository, BaseRepository<Car> carRepository) {
        this.rentalagreementRepository = rentalagreementRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

    public RentalAgreement findRentalAgreementById(Long rentalAgreementId) {
        return rentalagreementRepository.findById(rentalAgreementId);
    }

    public boolean createNewRentalAgreement(RentalAgreement rentalAgreement, Car car) {
        return false;

    }

}

