package com.example.projekt3_gruppe_7.repository;

import com.example.projekt3_gruppe_7.model.RentalAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RentalAgreementRepository {
    @Autowired

    private DataSource dataSource;

    public void createRentalAgreement(RentalAgreement agreement) {
        String sql = "INSERT INTO rental_agreement (customer_name, customer_email, chassis_number, " +
                "start_date, end_date, monthly_price, pickup_location) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, agreement.getCustomerName());
            statement.setString(2, agreement.getCustomerEmail());
            statement.setString(3, agreement.getChassisNumber());
            statement.setDate(4, Date.valueOf(agreement.getStartDate()));
            statement.setDate(5, Date.valueOf(agreement.getEndDate()));
            statement.setDouble(6, agreement.getMonthlyPrice());
            statement.setString(7, agreement.getPickupLocation());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isChassisNumberActivelyRented(String chassisNumber) {
        String sql = "SELECT * FROM rental_agreement WHERE chassis_number = ? AND end_date >= ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, chassisNumber);
            statement.setDate(2, Date.valueOf(LocalDate.now()));

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<RentalAgreement> getAllRentalAgreements() {
        List<RentalAgreement> agreements = new ArrayList<>();
        String sql = "SELECT * FROM rental_agreement";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                RentalAgreement agreement = new RentalAgreement(
                        resultSet.getLong("rental_agreement_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("customer_email"),
                        resultSet.getString("chassis_number"),
                        resultSet.getDate("start_date").toLocalDate(),
                        resultSet.getDate("end_date").toLocalDate(),
                        resultSet.getDouble("monthly_price"),
                        resultSet.getString("pickup_location")
                );
                agreements.add(agreement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agreements;
    }
}

