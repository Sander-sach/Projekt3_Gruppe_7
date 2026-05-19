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
    public CarController (CarService carService) {
        this.carService = carService;
    }
    @GetMapping("/cars/storage")
    public String carStorage(Model model){
        model.addAttribute("rented",carService.getRented());
        model.addAttribute("available",carService.getAvaliable());
        model.addAttribute("returned",carService.getReturned());
        model.addAttribute("awaitingRegistration", carService.getAwaitingRegistration());
        model.addAttribute("underRepair",carService.getUnderRepair());
        return "cars-storage";
    }
    @GetMapping("/cars/rented")
    public String carsRented(Model model){
        model.addAttribute("rented",carService.getRented());
        return "cars-rented";
    }
    @GetMapping("/cars/overview")
    public String carsOverview(Model model){
        model.addAttribute("rented",carService.getRented());
        model.addAttribute("available",carService.getAvaliable());
        model.addAttribute("returned",carService.getReturned());
        model.addAttribute("awaitingRegistration", carService.getAwaitingRegistration());
        model.addAttribute("underRepair",carService.getUnderRepair());
        return "cars-overview";
    }


}
