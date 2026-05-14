package com.example.projekt3_gruppe_7.controller;

import com.example.projekt3_gruppe_7.model.CarRegistration;
import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.RentalAgreement;
import com.example.projekt3_gruppe_7.service.CarRegistrationService;
import com.example.projekt3_gruppe_7.service.CarService;
import com.example.projekt3_gruppe_7.service.RentalAgreementService;
import com.example.projekt3_gruppe_7.service.RentalAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarRegistrationController {

    private final CarRegistrationService carRegistrationService;
    private final RentalAgreementService rentalAgreementService;
    private final CarService carService;

    @Autowired
    public CarRegistrationController(CarRegistrationService carRegistrationService, RentalAgreementService rentalAgreementService, CarService carService) {
        this.carRegistrationService = carRegistrationService;
        this.rentalAgreementService = rentalAgreementService;
        this.carService = carService;
    }

    // Viser liste over lejeaftaler uden registrering
    @GetMapping("/car-registration-overview")
    public String registrationOverview(Model model) throws Exception {
        List<RentalAgreement> missingRegistrations = carRegistrationService.findAgreementsWithoutRegistration();
        if (missingRegistrations == null) {
            missingRegistrations = new ArrayList<>();
        }
        model.addAttribute("missingRegistrations", missingRegistrations);
        return "car-registration-overview";
    }

    // Henter bil og lejeaftale og viser tjeklisten
    @GetMapping("/car-registration-new/{rentalAgreementId}")
    public String newCarRegistration(@PathVariable Long rentalAgreementId, Model model) throws Exception {
        RentalAgreement rentalAgreement = rentalAgreementService.findRentalAgreementById(rentalAgreementId);
        Car car = carService.findCarById(rentalAgreement.getCarId());

        model.addAttribute("rentalAgreement", rentalAgreement);
        model.addAttribute("car", car);
        model.addAttribute("carRegistration", new CarRegistration());

        return "car-registration-new";
    }

    // Gemmer den udfyldte tjekliste og opdaterer bilens status
    @PostMapping("car-registration-new")
    public String saveCarRegistration(@ModelAttribute CarRegistration carRegistration) throws Exception {
        carRegistrationService.complete(carRegistration);
        return "redirect:/car-registration-overview";
    }
}
