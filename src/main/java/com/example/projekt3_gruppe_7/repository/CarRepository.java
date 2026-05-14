package com.example.projekt3_gruppe_7.repository;

import com.example.projekt3_gruppe_7.model.Car;
import java.util.List;

public interface CarRepository extends BaseRepository<Car> {
    Car findById(Long id);
    List<Car> findAll();
    void save(Car entity);
    void update(Car entity);
    void delete(Long id);
}