package com.example.projekt3_gruppe_7.repository;

import com.example.projekt3_gruppe_7.model.CarRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public interface CarRegistrationRepository extends BaseRepository<CarRegistration> {
    CarRegistration findById(Long id) throws SQLException;

    List<CarRegistration> findAll() throws SQLException;

    void save(CarRegistration entity) throws  SQLException;

    void update(CarRegistration entity) throws SQLException;

    void delete(Long id) throws SQLException;

}
