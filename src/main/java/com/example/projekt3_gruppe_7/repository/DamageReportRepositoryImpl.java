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

@Repository
public class DamageReportRepositoryImpl implements DamageReportRepository{

    @Autowired
    private DataSource dataSource;

    public DamageReport findById(Long id){
        return null;
    }

    public List<DamageReport> findAll(){
        List<DamageReport> list = new ArrayList<>();
        String sql = "SELECT * FROM damage_report";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                list.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(DamageReport damageReport){
        String sql = "INSERT INTO damage_report (car_id, report_date) VALUES (?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1, damageReport.getCarId());
            statement.setDate(4, java.sql.Date.valueOf(damageReport.getReportDate()));
            statement.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(DamageReport damageReport){

    }

    public void delete(Long id){

    }
    private DamageReport mapRow(ResultSet resultSet) throws SQLException {
        Long damageReportId = resultSet.getLong("report_id");
        Long carId = resultSet.getLong("car_id");
        LocalDate reportDate = resultSet.getDate("report_date").toLocalDate();
        return new DamageReport(damageReportId, carId, reportDate);
    }
}
