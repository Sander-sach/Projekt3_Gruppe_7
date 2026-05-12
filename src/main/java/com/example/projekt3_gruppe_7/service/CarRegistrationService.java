package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.CarRegistration;
import com.example.projekt3_gruppe_7.model.CarStatus;
import com.example.projekt3_gruppe_7.model.RentalAgreement;
import com.example.projekt3_gruppe_7.repository.CarRegistrationRepository;
import com.example.projekt3_gruppe_7.repository.CarRepository;
import com.example.projekt3_gruppe_7.repository.RentalAgreementRepository;

import java.util.ArrayList;
import java.util.List;

public class CarRegistrationService {

    private final CarRepository carRepository;
    private final CarRegistrationRepository carRegistrationRepository;
    private final RentalAgreementRepository rentalAgreementRepository;

    public CarRegistrationService(CarRepository carRepository,
                                  CarRegistrationRepository carRegistrationRepository,
                                  RentalAgreementRepository rentalAgreementRepository) {
        this.carRepository = carRepository;
        this.carRegistrationRepository = carRegistrationRepository;
        this.rentalAgreementRepository = rentalAgreementRepository;
    }

    // Gemmer registreringen og opdaterer bilens status til AVAILABLE
    public void complete(CarRegistration form) throws Exception {
        validate(form);
        carRegistrationRepository.save(form);
        updateCarStatus(form.getRentalAgreementId());
    }

    // Returnerer alle lejeaftaler hvor bilens registrering mangler
    public List<RentalAgreement> findAgreementsWithoutRegistration() throws Exception {
        List<RentalAgreement> alleAftaler = rentalAgreementRepository.findAll();
        List<CarRegistration> alleRegistreringer = carRegistrationRepository.findAll();

        // Saml alle rentalAgreementId'er der allerede har en registrering
        List<Long> registreredeIds = new ArrayList<>();
        for (CarRegistration cr : alleRegistreringer) {
            registreredeIds.add(cr.getRentalAgreementId());
        }

        // Returner kun de aftaler der IKKE har en registrering
        List<RentalAgreement> manglerRegistrering = new ArrayList<>();
        for (RentalAgreement aftale : alleAftaler) {
            if (!registreredeIds.contains(aftale.getAgreementId())) {
                manglerRegistrering.add(aftale);
            }
        }
        return manglerRegistrering;
    }

    // Finder bilen via rentalAgreementId og sætter status til AVAILABLE
    private void updateCarStatus(Long rentalAgreementId) throws Exception {
        Car car = carRepository.findByRentalAgreementId(rentalAgreementId);
        if (car == null) {
            throw new IllegalArgumentException("Ingen bil fundet for lejeaftale-ID: " + rentalAgreementId);
        }
        car.setStatus(CarStatus.AVAILABLE);
        carRepository.update(car);
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