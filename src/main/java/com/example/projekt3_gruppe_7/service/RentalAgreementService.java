package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.CarStatus;
import com.example.projekt3_gruppe_7.model.RentalAgreement;
import com.example.projekt3_gruppe_7.repository.CarRepository;
import com.example.projekt3_gruppe_7.repository.RentalAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalAgreementService {

    private final RentalAgreementRepository rentalAgreementRepository;
    private final CarRepository carRepository;

    @Autowired
    public RentalAgreementService(RentalAgreementRepository rentalAgreementRepository, CarRepository carRepository) {
        this.rentalAgreementRepository = rentalAgreementRepository;
        this.carRepository = carRepository;
    }

    // Validerer at ingen felter er tomme eller null
    private boolean validateFields(RentalAgreement agreement) {
        if (agreement.getCarId() == null)
            return false;

        if (agreement.getCostumerId() == null)
            return false;

        if (agreement.getStartDate() == null || agreement.getEndDate() == null)
            return false;

        if (agreement.getLocation() == null)
            return false;

        if (agreement.getSubscriptionType() == null)
            return false;

        return true;
    }

    // Validerer at slutdato ikke er før startdato
    private boolean validateDates(RentalAgreement agreement) {
        return agreement.getEndDate().isAfter(agreement.getStartDate());
    }

    // Validerer at bilen er tilgængelig (ikke allerede udlejet)
    private boolean validateCarIsAvailable(Long carId) {
        Car car = carRepository.findById(carId);
        if (car == null)
            return false;

        return car.getStatus() == CarStatus.AVAILABLE;
    }

    // Opdaterer bilens status — kaldes kun internt fra create()
    private void updateCarStatus(Long carId, CarStatus status) {
        Car car = carRepository.findById(carId);
        car.setStatus(status);
        carRepository.update(car);
    }

    // Hovedmetode der kaldes fra controlleren.
    // Returnerer true hvis aftalen blev oprettet, false hvis noget fejlede
    public boolean create(RentalAgreement agreement) {
        if (!validateFields(agreement))
            return false;

        if (!validateDates(agreement))
            return false;

        if (!validateCarIsAvailable(agreement.getCarId()))
            return false;

        rentalAgreementRepository.save(agreement);
        updateCarStatus(agreement.getCarId(), CarStatus.RENTED);
        return true;
    }

    // Henter en specifik aftale på baggrund af id
    public RentalAgreement findRentalAgreementById(Long rentalAgreementId) {
        return rentalAgreementRepository.findById(rentalAgreementId);
    }

    public List<RentalAgreement> getAllAgreements() {
        return rentalAgreementRepository.findAll();
    }
}