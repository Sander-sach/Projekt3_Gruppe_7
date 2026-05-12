package com.example.projekt3_gruppe_7.controller;

import org.springframework.ui.Model;
import com.example.projekt3_gruppe_7.model.RentalAgreement;
import com.example.projekt3_gruppe_7.service.RentalAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RentalAgreementController {

    private final RentalAgreementService rentalAgreementService;


    @Autowired

    public RentalAgreementController(RentalAgreementService rentalAgreementService) {

        this.rentalAgreementService = rentalAgreementService;
    }
    // Viser formularen til at oprette en ny lejeaftale
    // Sender et tomt RentalAgreement-objekt til Thymeleaf så formularen kan binde data
    @GetMapping("/rentalagreement/new")
    public String showNewRentalAgreementForm(Model model) {
        model.addAttribute("rentalAgreement", new RentalAgreement());
        return "newRentalAgreement"; // → templates/newRentalAgreement.html
        }

    @PostMapping("/rentalagreement/new")
    public String createRentalAgreement(@ModelAttribute RentalAgreement rentalAgreement, Model model){
        boolean succes = rentalAgreementService.create(rentalAgreement);

        if(succes){
            return "redirect:/rentalagreement?confirmation=true";
        }

        // hvis noget fejlede sendes brugeren tilbage til formularen med en fejlbesked

        model.addAttribute("error", true);
        model.addAttribute("rentalAgreement", rentalAgreement);
        return "newRentalAgreement";

    }

    //oversigtsside over alle lejeaftaler

    @GetMapping ("/rentalagreement")
    public String rentalAgreementOverview(Model model){
        model.addAttribute ("agreements", rentalAgreementService.getAllAgreements());
        return "rentalAgreements"; // → templates/rentalAgreements.html
    }
}
