package com.example.projekt3_gruppe_7.repository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import com.example.projekt3_gruppe_7.model.Employee;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository implements BaseRepository<Employee>{

    @Autowired
    private DataSource dataSource;

    public Employee findById(Long id){
        Employee employee =null;

        return employee;
    }

    public List<Employee> findAll(){
        List<Employee> list = new ArrayList<>();

        return list;
    }

    public void save(Employee entity){

    }

    public void update(Employee entity){

    }

    public void delete(Long id){

    }
}
