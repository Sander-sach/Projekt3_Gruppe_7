package com.example.projekt3_gruppe_7.controller;

import com.example.projekt3_gruppe_7.model.*;
import com.example.projekt3_gruppe_7.service.CustomerService;
import com.example.projekt3_gruppe_7.service.RentalAgreementService;
import com.example.projekt3_gruppe_7.repository.CarRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class RentalAgreementController {

    private final RentalAgreementService rentalAgreementService;
    private final CarRepository carRepository;
    private final CustomerService customerService;

    @Autowired
    public RentalAgreementController(RentalAgreementService rentalAgreementService, CarRepository carRepository, CustomerService customerService) {
        this.rentalAgreementService = rentalAgreementService;
        this.carRepository = carRepository;
        this.customerService = customerService;
    }

    // Viser oversigt over alle lejeaftaler
    @GetMapping("/rental-overview")
    public String rental(Model model, HttpSession session) {
        //check EmployeeRole matcher side
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null){
            return "redirect:/login";
        }
        if(employee.getRole() != EmployeeRole.DATA_REGISTRATION && employee.getRole() != EmployeeRole.ADMIN) {
            return "redirect:/login";
        }

        List<RentalAgreement> agreements = rentalAgreementService.getAllAgreements();
        model.addAttribute("agreements", agreements);
        return "rental-overview";
    }

    // Viser formular til oprettelse af ny lejeaftale
    // Henter alle tilgængelige biler så brugeren kan vælge
    @GetMapping("/rental-agreement-new")
    public String newRentalAgreement(Model model, HttpSession session){
        //check EmployeeRole matcher side
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null){
            return "redirect:/login";
        }
        if(employee.getRole() != EmployeeRole.DATA_REGISTRATION && employee.getRole() != EmployeeRole.ADMIN){
            return "redirect:/login";
        }

        List<Car> availableCars = carRepository.findAll().stream()
                // lambda funktion for Car objekt svarer til check AVAILABLE status = true
                .filter(car -> car.getStatus() == CarStatus.AVAILABLE)
                .toList();
        model.addAttribute("availableCars", availableCars);
        return "rental-agreement-new";

    }

    // Modtager og gemmer en ny lejeaftale fra formularen
    @PostMapping("/rental-agreement-save")
    public String saveAgreement(@RequestParam Long carId, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate,@RequestParam Location location,
                                @RequestParam SubscriptionType subscriptionType,@RequestParam double monthlyPrice, @RequestParam String name,@RequestParam String phone,@RequestParam String email,  Model model){
        Customer customer = new Customer(name,phone,email);
        RentalAgreement rentalAgreement = new RentalAgreement(carId,startDate,endDate,location,subscriptionType,monthlyPrice);

        boolean customerCreated = customerService.create(customer);
        if (!customerCreated) {
            model.addAttribute("errorCustomerForm", true);
            return "rental-agreement-new";
        }
        customer = customerService.findCustomerByPhone(customer.getPhone());
        rentalAgreement.setCustomerId(customer.getCustomerId());
        boolean agreementCreated = rentalAgreementService.create(rentalAgreement);

        if (!agreementCreated) {
            model.addAttribute("errorAgreementForm", true);
            return "rental-agreement-new";
        }
        model.addAttribute("successMessage", true);
        return "redirect:/rental-overview";
    }
}