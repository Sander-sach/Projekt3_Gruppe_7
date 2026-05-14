package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.repository.CarRegistrationRepositoryImpl;
import com.example.projekt3_gruppe_7.repository.CarRepositoryImpl;
import com.example.projekt3_gruppe_7.repository.RentalAgreementRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.CarRegistration;
import com.example.projekt3_gruppe_7.model.CarStatus;
import com.example.projekt3_gruppe_7.model.RentalAgreement;
import com.example.projekt3_gruppe_7.repository.BaseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarRegistrationService {

    private final CarRepositoryImpl carRepositoryImpl;
    private final CarRegistrationRepositoryImpl carRegistrationRepositoryImpl;
    private final RentalAgreementRepositoryImpl rentalAgreementRepositoryImpl;

    @Autowired
    public CarRegistrationService(CarRepositoryImpl carRepositoryImpl,
                                  CarRegistrationRepositoryImpl carRegistrationRepositoryImpl,
                                  RentalAgreementRepositoryImpl rentalAgreementRepositoryImpl) {
        this.carRepositoryImpl = carRepositoryImpl;
        this.carRegistrationRepositoryImpl = carRegistrationRepositoryImpl;
        this.rentalAgreementRepositoryImpl = rentalAgreementRepositoryImpl;
    }

    // Gemmer registreringen og opdaterer bilens status
    public boolean complete(CarRegistration form) throws Exception {
        if (!validateRegistration(form)) {
            return false;
        }
        carRegistrationRepositoryImpl.save(form);
        updateCarStatus(form.getRentalAgreementId());
        return true;
    }

    // Returnerer alle lejeaftaler der mangler registrering mangler
    public List<RentalAgreement> findAgreementsWithoutRegistration() throws Exception {
        List<RentalAgreement> list = new ArrayList<>();
        list = rentalAgreementRepositoryImpl.findRentalAgreementMissingRegistration();
        return list;
    }

    // Finder bilen via rentalAgreementId og sætter status
    private boolean updateCarStatus(Long rentalAgreementId) throws Exception {
        Car car = carRepositoryImpl.findById(rentalAgreementId);
        if (car == null) {
            return false;
        }
        car.setStatus(CarStatus.RENTED);
        carRepositoryImpl.update(car);
        return true;
    }

    private boolean validateRegistration(CarRegistration form) {
        if (form == null) return false;
        if (form.getLeasingCode() == null || form.getLeasingCode().isBlank()) return false;
        if (form.getIRKCode() == null || form.getIRKCode().isBlank()) return false;
        if (form.getPlateNumber() == null || form.getPlateNumber().isBlank()) return false;
        if (form.getRentalAgreementId() == null) return false;
        return true;
    }
}