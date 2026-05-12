package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.Employee;
import com.example.projekt3_gruppe_7.model.EmployeeRole;
import com.example.projekt3_gruppe_7.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final BCryptPasswordEncoder passwordEncoder;
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, BCryptPasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean createEmployeeUser(Employee employee){
        if(employeeRepository.checkUsernameExists(employee.getUserName())){
            return false;
        }
        if (!validatePasswordCharacters(employee.getPassword())) {
            return false;
        }
        employee.setPassword(hashPassword(employee.getPassword()));
        employeeRepository.save(employee);
        return true;
    }

    public boolean validatePasswordCharacters(String password){
        if(!password.matches(".*[0-9].*") || password.length() < 8){
            return false;
        }
        return true;
    }


    public EmployeeRole hasAccess(Employee employee){
        EmployeeRole role = null;

        return role;
    }

    public String hashPassword(String plainPassword){
        return passwordEncoder.encode(plainPassword);
    }
    public boolean varifyPassword(String plainPassword, String hashedPassword){
       return passwordEncoder.matches(plainPassword,hashedPassword);
    }

    public Employee login(String username, String plainPassword){
        Employee employee = employeeRepository.findByUsername(username);
            if(employee != null && varifyPassword(plainPassword,employee.getPassword())){
                return employee;
            }
            return null;
    }

}
