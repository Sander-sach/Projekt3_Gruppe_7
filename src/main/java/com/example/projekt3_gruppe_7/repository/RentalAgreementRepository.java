package com.example.projekt3_gruppe_7.repository;
import com.example.projekt3_gruppe_7.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public interface RentalAgreementRepository extends BaseRepository<RentalAgreement> {

    RentalAgreement findById(Long id) throws SQLException;

   List<RentalAgreement> findAll() throws SQLException;

    void save(RentalAgreement entity) throws SQLException;

    void update(RentalAgreement entity) throws SQLException;

    void delete(Long id) throws SQLException;

    // Søger i DB med Left join ved brug af foreign key i car_registration
    List<RentalAgreement> findRentalAgreementMissingRegistration() throws SQLException;

}
