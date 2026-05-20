package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.repository.CarRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepositoryImpl carRepositoryImpl;

    @Autowired
    public CarService(CarRepositoryImpl carRepositoryImpl) {
        this.carRepositoryImpl = carRepositoryImpl;
    }

    public Car findCarById(Long carId){
        return carRepositoryImpl.findById(carId);
    }

    // Returnerer samlet månedlig pris på alle udlejede biler
    public double totalRentedPrice() {
        return carRepositoryImpl.getTotalRentedPrice();
    }

    // Returnerer alle biler med deres status (til oversigt)
    public List<Car> carsStatus() {
        return carRepositoryImpl.findAll();
    }

    // Returnerer kun udlejede biler
    public List<Car> rentedCars() {
        return carRepositoryImpl.findAllRented();
    }
}

