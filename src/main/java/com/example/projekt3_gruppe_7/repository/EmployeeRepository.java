package com.example.projekt3_gruppe_7.repository;
import com.example.projekt3_gruppe_7.model.EmployeeRole;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import com.example.projekt3_gruppe_7.model.Employee;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class EmployeeRepository {

    @Autowired
    private DataSource dataSource;


    public Employee findByUsername(String username){
        Employee employee = null;
        String sql = "SELECT * FROM employee WHERE username = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long employeeId = (resultSet.getLong("employee_id"));
                    String name = (resultSet.getString("name"));
                    EmployeeRole role = EmployeeRole.valueOf(resultSet.getString("role"));
                    String password = (resultSet.getString("password"));
                    employee = new Employee(employeeId, name, role, username, password);
                    return employee;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Employee employee) {
        String sql = "INSERT INTO employee (name, username, password, role) VALUES (?, ?, ?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getUserName());
            statement.setString(3, employee.getPassword());
            statement.setString(4, employee.getRole().name());
            statement.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkUsernameExists(String username){
        String sql = "SELECT COUNT(*) FROM employee WHERE username = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

