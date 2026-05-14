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
public class CustomerRepository implements BaseRepository<Customer>{

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

    public void save(Customer customer){
        String sql = "INSERT INTO customer (name,phone,email) VALUES (?, ?, ?)";
        try(Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPhone());
            ps.setString(3, customer.getEmail());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(Customer entity){

    }

    public void delete(Long id){

    }
}
