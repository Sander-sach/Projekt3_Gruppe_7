package com.example.projekt3_gruppe_7.controller;

import com.example.projekt3_gruppe_7.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String login(){
        return "/login";
    }
    @GetMapping("/registeruser")
    public String registerUser(){
        return "/registeruser";
    }

}
