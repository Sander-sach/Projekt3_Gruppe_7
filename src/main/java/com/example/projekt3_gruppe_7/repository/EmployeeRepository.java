package com.example.projekt3_gruppe_7.repository;

import com.example.projekt3_gruppe_7.model.Employee;
import java.util.List;

public interface EmployeeRepository extends BaseRepository<Employee>{
    Employee findById(Long id);

    List<Employee> findAll();

    void save(Employee entity);

    void update(Employee entity);

    void delete(Long id);

    Employee findByUsername(String username);

    boolean checkUsernameExists(String username);

}
