package com.example.projekt3_gruppe_7.repository;

import com.example.projekt3_gruppe_7.model.Costumer;
import com.example.projekt3_gruppe_7.model.DamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DamageReportRepositoryImpl implements DamageReportRepository{

    @Autowired
    private DataSource dataSource;

    public DamageReport findById(Long id){
        DamageReport damageReport = null;

        return damageReport;
    }

    public List<DamageReport> findAll(){
        List<DamageReport> list = new ArrayList<>();

        return list;
    }

    public void save(DamageReport entity){

    }

    public void update(DamageReport entity){

    }

    public void delete(Long id){

    }
}
