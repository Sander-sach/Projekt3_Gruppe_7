package com.example.projekt3_gruppe_7.repository;

import com.example.projekt3_gruppe_7.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CostumerRepository implements BaseRepository<Customer>{

    @Autowired
    private DataSource dataSource;

    public Customer findById(Long id){
        Customer customer =null;

        return customer;
    }

    public List<Customer> findAll(){
        List<Customer> list = new ArrayList<>();

        return list;
    }

    public void save(Customer entity){

    }

    public void update(Customer entity){

    }

    public void delete(Long id){

    }
}
