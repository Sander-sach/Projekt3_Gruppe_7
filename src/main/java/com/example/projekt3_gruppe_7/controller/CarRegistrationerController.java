package com.example.projekt3_gruppe_7.controller;

import com.example.projekt3_gruppe_7.model.CarRegistration;
import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.RentalAgreement;
import com.example.projekt3_gruppe_7.service.CarRegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarRegistrationController {

    private final CarRegistrationService carRegistrationService;

    public CarRegistrationController(CarRegistrationService carRegistrationService) {
        this.carRegistrationService = carRegistrationService;
    }

    // Viser liste over lejeaftaler uden registrering
    @GetMapping("/registrations")
    public String registrations(Model model) throws Exception {
        List<RentalAgreement> aftaler = carRegistrationService.findAgreementsWithoutRegistration();
        model.addAttribute("aftaler", aftaler);
        return "registrations";
    }

    // Henter bil og lejeaftale og viser tjeklisten
    @GetMapping("/delivery/{rentalAgreementId}")
    public String newCarRegistration(@PathVariable Long rentalAgreementId, Model model) throws Exception {
        RentalAgreement rentalAgreement = carRegistrationService.findRentalAgreementById(rentalAgreementId);
        Car car = carRegistrationService.findCarByRentalAgreementId(rentalAgreementId);

        model.addAttribute("rentalAgreement", rentalAgreement);
        model.addAttribute("car", car);
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
