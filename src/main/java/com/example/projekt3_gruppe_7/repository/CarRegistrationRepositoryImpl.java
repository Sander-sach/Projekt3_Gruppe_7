package com.example.projekt3_gruppe_7.repository;

import com.example.projekt3_gruppe_7.model.CarRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRegistrationRepositoryImpl implements CarRegistrationRepository {

    @Autowired
    private final DataSource dataSource;

    public CarRegistrationRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    // Hent én registrering baseret på ID
    public CarRegistration findById(Long id) throws SQLException {
        Connection con = dataSource.getConnection();
        String sql = "SELECT * FROM car_registration WHERE car_registration_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            CarRegistration cr = new CarRegistration();
            cr.setCarRegistrationId(rs.getLong("car_registration_id"));
            cr.setLeasingCode(rs.getString("leasing_code"));
            cr.setIRKCode(rs.getString("irk_code"));
            cr.setPlateNumber(rs.getString("plate_number"));
            cr.setRentalAgreementId(rs.getLong("rental_agreement_id"));
            return cr;
        }
        return null;
    }




    // Hent alle registreringer
    public List<CarRegistration> findAll() throws SQLException {
        Connection con = dataSource.getConnection();
        String sql = "SELECT * FROM car_registration";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<CarRegistration> list = new ArrayList<>();
        while (rs.next()) {
            CarRegistration cr = new CarRegistration();
            cr.setCarRegistrationId(rs.getLong("car_registration_id"));
            cr.setLeasingCode(rs.getString("leasing_code"));
            cr.setIRKCode(rs.getString("irk_code"));
            cr.setPlateNumber(rs.getString("plate_number"));
            cr.setRentalAgreementId(rs.getLong("rental_agreement_id"));
            list.add(cr);
        }
        return list;
    }

    // Gem en ny registrering i databasen
    public void save(CarRegistration cr) throws SQLException {
        Connection con = dataSource.getConnection();
        String sql = "INSERT INTO car_registration (leasing_code, irk_code, plate_number, rental_agreement_id) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cr.getLeasingCode());
        ps.setString(2, cr.getIRKCode());
        ps.setString(3, cr.getPlateNumber());
        ps.setLong(4, cr.getRentalAgreementId());
        ps.executeUpdate();
    }

    // Opdater en eksisterende registrering
    public void update(CarRegistration cr) throws SQLException {
        Connection con = dataSource.getConnection();
        String sql = "UPDATE car_registration SET leasing_code = ?, irk_code = ?, plate_number = ?, rental_agreement_id = ? WHERE car_registration_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cr.getLeasingCode());
        ps.setString(2, cr.getIRKCode());
        ps.setString(3, cr.getPlateNumber());
        ps.setLong(4, cr.getRentalAgreementId());
        ps.setLong(5, cr.getCarRegistrationId());
        ps.executeUpdate();
    }

    // Slet en registrering baseret på ID
    public void delete(Long id) throws SQLException {
        Connection con = dataSource.getConnection();
        String sql = "DELETE FROM car_registration WHERE car_registration_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        ps.executeUpdate();
    }
}
