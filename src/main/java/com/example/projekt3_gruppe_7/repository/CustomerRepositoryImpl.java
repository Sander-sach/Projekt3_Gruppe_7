package com.example.projekt3_gruppe_7.repository;

import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.CarStatus;
import com.example.projekt3_gruppe_7.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
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

            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Customer mapRow(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(resultSet.getLong("customer_id"));
        customer.setName(resultSet.getString("name"));
        customer.setPhone(resultSet.getString("phone"));
        customer.setEmail(resultSet.getString("email"));

        return customer;
    }
    public Customer findCustomerByReportId(Long reportId){
        String sql = "SELECT c.* FROM customer c JOIN rental_agreement ra ON ra.customer_id = c.customer_id \n"+
                "JOIN damage_report dr ON dr.report_id = ra.report_id WHERE dr.report_id = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, reportId);

            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                     return mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
