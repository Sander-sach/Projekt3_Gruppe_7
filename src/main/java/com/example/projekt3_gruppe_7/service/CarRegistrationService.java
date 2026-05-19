package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.CarRegistration;
import com.example.projekt3_gruppe_7.model.CarStatus;
import com.example.projekt3_gruppe_7.model.RentalAgreement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarRegistrationService {

    private final CarRepository carRepository;
    private final CarRegistrationRepository carRegistrationRepository;
    private final RentalAgreementRepository rentalAgreementRepository;

    @Autowired
    public CarRegistrationService(CarRepository carRepository,
                                  CarRegistrationRepository carRegistrationRepository,
                                  RentalAgreementRepository rentalAgreementRepository) {
        this.carRepository = carRepository;
        this.carRegistrationRepository = carRegistrationRepository;
        this.rentalAgreementRepository = rentalAgreementRepository;
    }


    // Gemmer registreringen og opdaterer bilens status
    public boolean complete(CarRegistration form) {
        if (!validateRegistration(form)) {
            return false;
        }
        carRegistrationRepository.save(form);
        updateCarStatus(form.getRentalAgreementId());
        return true;
    }

    // Returnerer alle lejeaftaler der mangler registrering mangler
    public List<RentalAgreement> findAgreementsWithoutRegistration() {
        List<RentalAgreement> list;
        list = rentalAgreementRepository.findRentalAgreementMissingRegistration();
        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    // Finder bilen via rentalAgreementId og sætter status
    private boolean updateCarStatus(Long rentalAgreementId){
        Car car = carRepository.findByRentalAgreementId(rentalAgreementId);
        if (car == null) {
            return false;
        }
        car.setStatus(CarStatus.RENTED);
        carRepository.update(car);
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