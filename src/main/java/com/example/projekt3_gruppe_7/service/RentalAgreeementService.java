package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.RentalAgreement;
import com.example.projekt3_gruppe_7.repository.BaseRepository;
import com.example.projekt3_gruppe_7.repository.RentalAgreementRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalAgreeementService {

    private final RentalAgreementRepositoryImpl rentalagreementRepositoryImpl;

    @Autowired
    public RentalAgreeementService(RentalAgreementRepositoryImpl rentalagreementRepositoryImpl) {
        this.rentalagreementRepositoryImpl = rentalagreementRepositoryImpl;
    }

    public RentalAgreement findRentalAgreementById(Long rentalAgreementId){
        return rentalagreementRepositoryImpl.findById(rentalAgreementId);

    }
}
