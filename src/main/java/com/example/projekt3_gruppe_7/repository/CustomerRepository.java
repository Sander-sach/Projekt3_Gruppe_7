package com.example.projekt3_gruppe_7.repository;

import com.example.projekt3_gruppe_7.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CustomerRepository extends BaseRepository<Customer>{


    Customer findById(Long id);

    List<Customer> findAll();

    void save(Customer customer);

    void update(Customer entity);

    void delete(Long id);

    Customer findByPhone(String phone);

}
