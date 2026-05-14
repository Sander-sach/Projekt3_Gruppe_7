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
    RentalAgreement findById(Long id);
    List<RentalAgreement> findAll();
    void save(RentalAgreement entity);
    void update(RentalAgreement entity);
    void delete(Long id);
    List<RentalAgreement> findRentalAgreementMissingRegistration();
}
