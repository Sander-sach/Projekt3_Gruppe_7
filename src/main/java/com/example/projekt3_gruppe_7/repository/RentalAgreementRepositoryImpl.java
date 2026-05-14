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
    @Autowired
    private DataSource dataSource;

    public RentalAgreement findById(Long id){
        RentalAgreement rentalAgreement = null;
        String sql = "SELECT * FROM rental_agreement WHERE agreement_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
             statement.setLong(1, id);

             try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    rentalAgreement = mapRow(resultSet);
                }
             }
        }catch (SQLException e){
            e.printStackTrace();
        }

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

    // Søger i DB med Left join ved brug af foreign key i car_registration
    public List<RentalAgreement> findRentalAgreementMissingRegistration() throws SQLException {
        List<RentalAgreement> list = new ArrayList<>();
        String sql = "SELECT ra.* FROM rental_agreement ra " +
                "LEFT JOIN car_registration cr ON ra.agreement_id = cr.agreement_id " +
                "WHERE cr.agreement_id IS NULL";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()) {
                list.add(mapRow(resultSet));
            }
        }
        return list;
    }

    private RentalAgreement mapRow(ResultSet resultSet)throws SQLException{
        Long agreementId = resultSet.getLong("agreement_id");
        Long carId = resultSet.getLong("car_id");
        Long costumerId = resultSet.getLong("costumer_id");
        Long damageReportId = resultSet.getLong("report_id");
        LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
        LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
        Location location = Location.valueOf(resultSet.getString("location"));
        SubscriptionType subscriptionType = SubscriptionType.valueOf(resultSet.getString("subscription_type"));

        return new RentalAgreement(agreementId,carId,costumerId,damageReportId,startDate,endDate,location,subscriptionType);
    }
}
