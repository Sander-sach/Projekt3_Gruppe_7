package com.example.projekt3_gruppe_7.controller;

import com.example.projekt3_gruppe_7.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    // Oversigt til forretningsudvikler
    @GetMapping("/cars/overview")
    public String carOverview(Model model) {
        model.addAttribute("allCars", carService.carsStatus());
        model.addAttribute("totalPrice", carService.totalRentedPrice());
        return "car-overview"; // navn på din Thymeleaf HTML-fil
    }

    // Liste over udlejede biler
    @GetMapping("/cars/rented")
    public String carsRented(Model model) {
        model.addAttribute("rentedCars", carService.rentedCars());
        return "cars-rented";
    }
}