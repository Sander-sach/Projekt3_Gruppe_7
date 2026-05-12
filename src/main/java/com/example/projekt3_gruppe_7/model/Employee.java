package com.example.projekt3_gruppe_7.model;

public class Employee {
    private Long employeeId;
    private String name;
    private EmployeeRole role;
    private String userName;
    private String password;

    public Employee(Long employeeId, String name, EmployeeRole role, String userName, String password) {
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
        this.userName = userName;
        this.password = password;
    }

    public Employee(String name, EmployeeRole role, String userName, String password) {
        this.name = name;
        this.role = role;
        this.userName = userName;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
