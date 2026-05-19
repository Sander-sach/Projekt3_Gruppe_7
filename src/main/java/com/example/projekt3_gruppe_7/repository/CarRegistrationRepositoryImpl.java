package com.example.projekt3_gruppe_7.repository;

import com.example.projekt3_gruppe_7.model.CarRegistration;
import com.example.projekt3_gruppe_7.model.Location;
import com.example.projekt3_gruppe_7.model.RentalAgreement;
import com.example.projekt3_gruppe_7.model.SubscriptionType;
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

@Repository
public class CarRegistrationRepositoryImpl implements CarRegistrationRepository {

    @Autowired
    private final DataSource dataSource;

    public CarRegistrationRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    // Hent én registrering baseret på ID
    public CarRegistration findById(Long id) {
        String sql = "SELECT * FROM car_registration WHERE car_registration_id = ?";
        try(Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return mapRow(resultSet);
            }
        }catch (SQLException e){

        }
        return null;
    }




    // Hent alle registreringer
    public List<CarRegistration> findAll() throws SQLException {
        Connection con = dataSource.getConnection();
        String sql = "SELECT * FROM car_registration";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();

        List<CarRegistration> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    // Gem en ny registrering i databasen
    public void save(CarRegistration cr) {
        String sql = "INSERT INTO car_registration (leasing_code, irk_code, plate_number, agreement_id) VALUES (?, ?, ?, ?)";
        try(Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);){
            ps.setString(1, cr.getLeasingCode());
            ps.setString(2, cr.getIRKCode());
            ps.setString(3, cr.getPlateNumber());
            ps.setLong(4, cr.getRentalAgreementId());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Opdater en eksisterende registrering
    public void update(CarRegistration cr) {
        String sql = "UPDATE car_registration SET leasing_code = ?, irk_code = ?, plate_number = ?, agreement_id = ? WHERE car_registration_id = ?";
        try(Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){

        ps.setString(1, cr.getLeasingCode());
        ps.setString(2, cr.getIRKCode());
        ps.setString(3, cr.getPlateNumber());
        ps.setLong(4, cr.getRentalAgreementId());
        ps.setLong(5, cr.getCarRegistrationId());
        ps.executeUpdate();}catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Slet en registrering baseret på ID
    public void delete(Long id) throws SQLException {
        Connection con = dataSource.getConnection();
        String sql = "DELETE FROM car_registration WHERE car_registration_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        ps.executeUpdate();
    }
    private CarRegistration mapRow(ResultSet resultSet) throws SQLException {
        Long carRegistrationId = resultSet.getLong("car_registration_id");
        String leasingCode =resultSet.getString("leasing_code");
        String irkCode = resultSet.getString("irk_code");
        String plateNumber = resultSet.getString("plate_number");
        Long rentalAgreementId = resultSet.getLong("agreement_id");

        CarRegistration carRegistration = new CarRegistration(carRegistrationId,leasingCode,irkCode,plateNumber,rentalAgreementId);
        return carRegistration;
    }
}
