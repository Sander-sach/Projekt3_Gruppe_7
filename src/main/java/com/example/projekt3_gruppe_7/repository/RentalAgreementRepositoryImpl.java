package com.example.projekt3_gruppe_7.repository;

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
public class RentalAgreementRepositoryImpl implements RentalAgreementRepository {

    private final DataSource dataSource;

    @Autowired
    public RentalAgreementRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public RentalAgreement findById(Long id) {
        RentalAgreement rentalAgreement = null;
        String sql = "SELECT * FROM rental_agreement WHERE agreement_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    rentalAgreement = mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentalAgreement;
    }

    public List<RentalAgreement> findAll() {
        List<RentalAgreement> list = new ArrayList<>();
        String sql = "SELECT * FROM rental_agreement";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                list.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void save(RentalAgreement entity) {
        String sql = "INSERT INTO rental_agreement (car_id, customer_id, report_id, start_date, end_date, monthly_price, location, subscription_type) VALUES (?, ?, ?, ?, ?, ?, ?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, entity.getCarId());
            statement.setLong(2, entity.getCustomerId());
            // damageReportId kan være null når aftalen oprettes
            if (entity.getDamageReportId() != null) {
                statement.setLong(3, entity.getDamageReportId());
            } else {
                statement.setNull(3, java.sql.Types.BIGINT);
            }
            statement.setDate(4, java.sql.Date.valueOf(entity.getStartDate()));
            statement.setDate(5, java.sql.Date.valueOf(entity.getEndDate()));
            statement.setDouble (6, entity.getMonthlyPrice());
            statement.setString(7, entity.getLocation().name());
            statement.setString(8, entity.getSubscriptionType().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(RentalAgreement entity) {
        String sql = "UPDATE rental_agreement SET car_id = ?, customer_id = ?, report_id = ?, start_date = ?, end_date = ?,monthly_price = ?, location = ?, subscription_type = ? WHERE agreement_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, entity.getCarId());
            statement.setLong(2, entity.getCustomerId());
            if (entity.getDamageReportId() != null) {
                statement.setLong(3, entity.getDamageReportId());
            } else {
                statement.setNull(3, java.sql.Types.BIGINT);
            }
            statement.setDate(4, java.sql.Date.valueOf(entity.getStartDate()));
            statement.setDate(5, java.sql.Date.valueOf(entity.getEndDate()));
            statement.setDouble (6, entity.getMonthlyPrice());
            statement.setString(7, entity.getLocation().name());
            statement.setString(8, entity.getSubscriptionType().name());
            statement.setLong(9, entity.getRentalagreementId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM rental_agreement WHERE agreement_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Søger i DB med Left join ved brug af foreign key i car_registration
    public List<RentalAgreement> findRentalAgreementMissingRegistration() {
        List<RentalAgreement> list = new ArrayList<>();
        String sql = "SELECT ra.* FROM rental_agreement ra " +
                "LEFT JOIN car_registration cr ON ra.agreement_id = cr.agreement_id " +
                "WHERE cr.agreement_id IS NULL";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                list.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private RentalAgreement mapRow(ResultSet resultSet) throws SQLException {
        Long agreementId = resultSet.getLong("agreement_id");
        Long carId = resultSet.getLong("car_id");
        Long costumerId = resultSet.getLong("customer_id");
        Long damageReportId = resultSet.getLong("report_id");
        if (resultSet.wasNull()) {
            damageReportId = null;
        }
        LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
        LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
        Location location = Location.valueOf(resultSet.getString("location"));
        SubscriptionType subscriptionType = SubscriptionType.valueOf(resultSet.getString("subscription_type"));
        double monthlyPrice = resultSet.getDouble("monthly_price");

        return new RentalAgreement(agreementId, carId, costumerId, damageReportId, startDate, endDate, location, subscriptionType,monthlyPrice);
    }
}