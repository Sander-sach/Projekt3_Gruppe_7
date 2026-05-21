package com.example.projekt3_gruppe_7.repository;

import com.example.projekt3_gruppe_7.model.DamageItem;
import com.example.projekt3_gruppe_7.model.RentalAgreement;
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
public class DamageItemRepositoryImpl {
    @Autowired
    DataSource dataSource;

    public DamageItem findById(Long id){
        return null;
    }

    public List<DamageItem> findAll(){
        return null;
    }

    public void save(DamageItem entity){

    }

    public void update(DamageItem entity){

    }

    public void delete(Long id){

    }
    public void saveAll(List<DamageItem> items, Long reportId) {
        if (items == null || items.isEmpty()) return;
        // StringBuilder brugt for at kunne burge "append" uden at skabe nye String objecter
        StringBuilder sql = new StringBuilder("INSERT INTO damage_item (report_id, description, price) VALUES ");

        for (int i = 0; i < items.size(); i++) {
            sql.append("(?, ?, ?)");
            if (i < items.size() - 1) {
                sql.append(", ");
            }
        }

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            int index = 1;
            for (DamageItem item : items) {
                ps.setLong(index++, reportId);
                ps.setString(index++, item.getDescription());
                ps.setDouble(index++, item.getPrice());
            }
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
