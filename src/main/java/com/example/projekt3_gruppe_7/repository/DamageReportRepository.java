package com.example.projekt3_gruppe_7.repository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import com.example.projekt3_gruppe_7.model.DamageReport;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DamageReportRepository implements BaseRepository<DamageReport>{

    @Autowired
    private DataSource dataSource;

    public DamageReport findById(Long id){
        DamageReport damageReport  =null;

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
