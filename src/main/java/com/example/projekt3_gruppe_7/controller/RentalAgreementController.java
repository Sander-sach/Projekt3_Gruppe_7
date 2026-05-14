package com.example.projekt3_gruppe_7.controller;

import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.CarStatus;
import com.example.projekt3_gruppe_7.model.RentalAgreement;
import com.example.projekt3_gruppe_7.service.RentalAgreementService;
import com.example.projekt3_gruppe_7.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RentalAgreementController {

    private final RentalAgreementService rentalAgreementService;
    private final CarRepository carRepository;

    @Autowired
    public RentalAgreementController(RentalAgreementService rentalAgreementService, CarRepository carRepository) {
        this.rentalAgreementService = rentalAgreementService;
        this.carRepository = carRepository;
    }

    // Viser oversigt over alle lejeaftaler
    @GetMapping("/rental")
    public String rental(Model model) {
        List<RentalAgreement> agreements = rentalAgreementService.getAllAgreements();
        model.addAttribute("agreements", agreements);
        return "rental";
    }

    // Viser formular til oprettelse af ny lejeaftale
    // Henter alle tilgængelige biler så brugeren kan vælge
    @GetMapping("/rental/new")
    public String newRentalAgreement(Model model) throws Exception {
        List<Car> availableCars = carRepository.findAll().stream()
                .filter(car -> car.getStatus() == CarStatus.AVAILABLE)
                .toList();
        model.addAttribute("availableCars", availableCars);
        model.addAttribute("rentalAgreement", new RentalAgreement(null, null, null, null, null, null));
        return "rental-agreement-new";

    }

    // Modtager og gemmer en ny lejeaftale fra formularen
    @PostMapping("/rental/R-A-new")
    public String saveAgreement(@ModelAttribute RentalAgreement agreement, Model model)throws Exception {
        boolean success = rentalAgreementService.create(agreement);

        if (!success) {
            List<Car> availableCars = carRepository.findAll().stream()
                    .filter(car -> car.getStatus() == CarStatus.AVAILABLE)
                    .toList();
            model.addAttribute("cars", availableCars);
            model.addAttribute("agreement", agreement);
            model.addAttribute("error", "Kunne ikke oprette lejeaftale. Tjek venligst alle felter.");
            return "rental-form";
        }
        return "redirect:/rental";
    }
}