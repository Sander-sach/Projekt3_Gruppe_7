package com.example.projekt3_gruppe_7.repository;

import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.CarStatus;
import com.example.projekt3_gruppe_7.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository{

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
    public Customer findByPhone(String phone){
        String sql = "SELECT * FROM customer WHERE phone = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, phone);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Customer mapRow(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getLong("customer_id"));
        customer.setName(rs.getString("name"));
        customer.setPhone(rs.getString("color"));
        customer.setEmail(rs.getString("maker"));

        return customer;
    }
}
