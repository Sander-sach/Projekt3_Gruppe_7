package com.example.projekt3_gruppe_7.service;

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

    private final BaseRepository<Car> carRepository;
    private final BaseRepository<CarRegistration> carRegistrationRepository;
    private final BaseRepository<RentalAgreement> rentalAgreementRepository;

    @Autowired
    public CarRegistrationService(BaseRepository<Car> carRepository,
                                  BaseRepository<CarRegistration> carRegistrationRepository,
                                  BaseRepository<RentalAgreement> rentalAgreementRepository) {
        this.carRepository = carRepository;
        this.carRegistrationRepository = carRegistrationRepository;
        this.rentalAgreementRepository = rentalAgreementRepository;
    }

    // Gemmer registreringen og opdaterer bilens status til AVAILABLE
    public void complete(CarRegistration form) throws Exception {
        if (!validate(form)) {
            throw new IllegalArgumentException("Registrering er ugyldig");
        }
        carRegistrationRepository.save(form);
        updateCarStatus(form.getRentalAgreementId());
    }

    // Returnerer alle lejeaftaler hvor bilens registrering mangler
    public List<RentalAgreement> findAgreementsWithoutRegistration() throws Exception {
        List<RentalAgreement> alleAftaler = rentalAgreementRepository.findAll();
        List<CarRegistration> alleRegistreringer = carRegistrationRepository.findAll();

        List<Long> registreredeIds = new ArrayList<>();
        for (CarRegistration cr : alleRegistreringer) {
            registreredeIds.add(cr.getRentalAgreementId());
        }

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
        Car car = carRepository.findById(rentalAgreementId);
        if (car == null) {
            throw new IllegalArgumentException("Ingen bil fundet for lejeaftale-ID: " + rentalAgreementId);
        }
        car.setStatus(CarStatus.AVAILABLE);
        carRepository.update(car);
    }

    private boolean validate(CarRegistration form) {
        if (form == null) return false;
        if (form.getLeasingCode() == null || form.getLeasingCode().isBlank()) return false;
        if (form.getIRKCode() == null || form.getIRKCode().isBlank()) return false;
        if (form.getPlateNumber() == null || form.getPlateNumber().isBlank()) return false;
        if (form.getRentalAgreementId() == null) return false;
        return true;
    }
}