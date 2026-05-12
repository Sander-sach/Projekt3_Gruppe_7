package com.example.projekt3_gruppe_7.controller;

import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.CarRegistration;
import com.example.projekt3_gruppe_7.model.RentalAgreement;
import com.example.projekt3_gruppe_7.repository.CarRepository;
import com.example.projekt3_gruppe_7.repository.RentalAgreementRepository;
import com.example.projekt3_gruppe_7.service.CarRegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarRegistrationController {

    private final CarRegistrationService carRegistrationService;
    private final CarRepository carRepository;
    private final RentalAgreementRepository rentalAgreementRepository;

    public CarRegistrationController(CarRegistrationService carRegistrationService,
                                     CarRepository carRepository,
                                     RentalAgreementRepository rentalAgreementRepository) {
        this.carRegistrationService = carRegistrationService;
        this.carRepository = carRepository;
        this.rentalAgreementRepository = rentalAgreementRepository;
    }

    // Viser liste over alle registreringer
    @GetMapping("/registrations")
    public String registrations(Model model) throws Exception {
        List<CarRegistration> registrations = carRegistrationService.findAll();
        model.addAttribute("registrations", registrations);
        return "registrations";
    }

    // Henter bil og lejeaftale og viser tjeklisten
    @GetMapping("/delivery/{carId}")
    public String newCarRegistration(@PathVariable Long carId, Model model) throws Exception {
        Car car = carRepository.findById(carId);
        RentalAgreement rentalAgreement = rentalAgreementRepository.findById(car.getRentalAgreementId());

        model.addAttribute("car", car);
        model.addAttribute("rentalAgreement", rentalAgreement);
        model.addAttribute("carRegistration", new CarRegistration());

        return "delivery";
    }

    // Gemmer den udfyldte tjekliste og opdaterer bilens status
    @PostMapping("/delivery/complete")
    public String saveCarRegistration(@ModelAttribute CarRegistration carRegistration) throws Exception {
        carRegistrationService.complete(carRegistration);
        return "redirect:/registrations";
    }
}
