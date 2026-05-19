package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.CarStatus;
import com.example.projekt3_gruppe_7.repository.CarRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Car> getCarByStatus(CarStatus status){
        List<Car> allCars = carRepositoryImpl.findAll();
        List<Car> result = new ArrayList<Car>();

        for (Car car : allCars){
            if (car.getStatus()== status){
                result.add(car);
            }
        }
        return result;

    }
    public List<Car> getRented(){
        return getCarByStatus(CarStatus.RENTED);
    }
    public List<Car> getAvaliable(){
        return getCarByStatus(CarStatus.AVAILABLE);
    }
    public List<Car> getReturned(){
        return getCarByStatus(CarStatus.RETURNED);
    }
    public List<Car> getAwaitingRegistration(){
        return getCarByStatus(CarStatus.AWAITING_REGISTRATION);
    }
    public List<Car> getUnderRepair(){
        return getCarByStatus(CarStatus.UNDER_REPAIR);
    }

}
