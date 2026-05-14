package com.example.projekt3_gruppe_7.repository;

import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CarRepository extends BaseRepository<Car> {
   Car findById(Long id) throws SQLException;

    List<Car> findAll() throws SQLException;

    void save(Car entity) throws  SQLException;

    void update(Car entity) throws SQLException;

    void delete(Long id) throws SQLException;

}