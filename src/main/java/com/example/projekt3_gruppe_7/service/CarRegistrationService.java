package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.CarRegistration;
import com.example.projekt3_gruppe_7.repository.CarRegistrationRepository;
import com.example.projekt3_gruppe_7.repository.CarRepository;

public class CarRegistrationService {

    private final CarRepository carRepository;
    private final CarRegistrationRepository carRegistrationRepository;

    public CarRegistrationService(CarRepository carRepository, CarRegistrationRepository carRegistrationRepository) {
        this.carRepository = carRepository;
        this.carRegistrationRepository = carRegistrationRepository;
    }

    public void complete(CarRegistration form) {
        validate(form);
        carRegistrationRepository.save(form);
    }

    private void validate(CarRegistration form) {
        if (form == null) {
            throw new IllegalArgumentException("Registrering må ikke være null");
        }
        if (form.getLeasingCode() == null || form.getLeasingCode().isBlank()) {
            throw new IllegalArgumentException("Leasingkode er påkrævet");
        }
        if (form.getIRKCode() == null || form.getIRKCode().isBlank()) {
            throw new IllegalArgumentException("IRK-kode er påkrævet");
        }
        if (form.getPlateNumber() == null || form.getPlateNumber().isBlank()) {
            throw new IllegalArgumentException("Nummerplade er påkrævet");
        }
        if (form.getRentalAgreementId() == null) {
            throw new IllegalArgumentException("Lejeaftale-ID er påkrævet");
        }
    }
}