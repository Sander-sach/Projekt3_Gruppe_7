package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.Customer;
import com.example.projekt3_gruppe_7.model.RentalAgreement;
import com.example.projekt3_gruppe_7.repository.BaseRepository;
import com.example.projekt3_gruppe_7.repository.RentalAgreementRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalAgreeementService {

    private final RentalAgreementRepositoryImpl rentalagreementRepositoryImpl;
    private final BaseRepository<Customer> customerRepository;
    private final BaseRepository<Car> carRepository;

    @Autowired
    public RentalAgreeementService(RentalAgreementRepositoryImpl rentalagreementRepositoryImpl, BaseRepository<Customer> customerRepository, BaseRepository<Car> carRepository) {
        this.rentalagreementRepositoryImpl = rentalagreementRepositoryImpl;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

    public RentalAgreement findRentalAgreementById(Long rentalAgreementId) {
        return rentalagreementRepositoryImpl.findById(rentalAgreementId);

    }

    public boolean createNewRentalAgreement(RentalAgreement rentalAgreement, Car car) {
        return false;

    }

}

