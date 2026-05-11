package com.example.projekt3_gruppe_7.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.projekt3_gruppe_7.model.RentalAgreement;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RentalAgreementRepository implements BaseRepository<RentalAgreement> {

    @Autowired
    private DataSource dataSource;

    public RentalAgreement findById(Long id){
        RentalAgreement rentalAgreement =null;

        return rentalAgreement;
    }

    public List<RentalAgreement> findAll(){
        List<RentalAgreement> list = new ArrayList<>();

        return list;
    }

    public void save(RentalAgreement entity){

    }

    public void update(RentalAgreement entity){

    }

    public void delete(Long id){

    }
}
