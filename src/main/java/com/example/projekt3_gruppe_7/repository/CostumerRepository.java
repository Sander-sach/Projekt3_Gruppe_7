package com.example.projekt3_gruppe_7.repository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import com.example.projekt3_gruppe_7.model.Costumer;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CostumerRepository implements BaseRepository<Costumer>{

    @Autowired
    private DataSource dataSource;

    public Costumer findById(Long id){
        Costumer costumer =null;

        return costumer;
    }

    public List<Costumer> findAll(){
        List<Costumer> list = new ArrayList<>();

        return list;
    }

    public void save(Costumer entity){

    }

    public void update(Costumer entity){

    }

    public void delete(Long id){

    }
}
