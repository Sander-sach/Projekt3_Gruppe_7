package com.example.projekt3_gruppe_7.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.projekt3_gruppe_7.model.Car;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository implements BaseRepository<Car>{

    @Autowired
    private DataSource dataSource;

    public Car findById(Long id){
        Car car =null;

        return car;
    }

    public List<Car> findAll(){
        List<Car> list = new ArrayList<>();

        return list;
    }

    public void save(Car entity){

    }

    public void update(Car entity){

    }

    public void delete(Long id){

    }
}
