package com.example.projekt3_gruppe_7.repository;

import com.example.projekt3_gruppe_7.model.CarRegistration;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRegistrationRepository implements BaseRepository<CarRegistration> {

    @Autowired
    private DataSource dataSource;

    public CarRegistration findById(Long id){
        CarRegistration carRegistration=null;

        return carRegistration;
    }

    public List<CarRegistration> findAll(){
    List<CarRegistration> list = new ArrayList<>();

    return list;
    }

    public void save(CarRegistration entity){

    }

    public void update(CarRegistration entity){

    }

    public void delete(Long id){

    }

}
