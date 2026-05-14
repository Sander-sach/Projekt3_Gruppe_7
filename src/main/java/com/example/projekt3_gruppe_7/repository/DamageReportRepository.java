package com.example.projekt3_gruppe_7.repository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import com.example.projekt3_gruppe_7.model.DamageReport;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public interface DamageReportRepository extends BaseRepository<DamageReport>{

    DamageReport findById(Long id)throws SQLException;

    List<DamageReport> findAll()throws SQLException;

    void save(DamageReport entity)throws SQLException;

    void update(DamageReport entity)throws SQLException;

    void delete(Long id)throws SQLException;
}
