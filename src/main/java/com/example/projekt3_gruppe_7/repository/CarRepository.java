package com.example.projekt3_gruppe_7.repository;

import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository implements BaseRepository<Car> {
    @Autowired
    private final DataSource dataSource;
    public CarRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    // Hent én bil baseret på ID
    public Car findById(Long id) throws SQLException {
        Connection con = dataSource.getConnection();
        String sql = "SELECT * FROM car WHERE car_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return mapRow(rs);
        }
        return null;
    }

    // Hent alle biler
    public List<Car> findAll() throws SQLException {
        Connection con = dataSource.getConnection();
        String sql = "SELECT * FROM car";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Car> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapRow(rs));
        }
        return list;
    }

    // Hent bil via lejeaftale-ID (bruges til at opdatere bilstatus)
    public Car findByRentalAgreementId(Long rentalAgreementId) throws SQLException {
        Connection con = dataSource.getConnection();
        String sql = "SELECT * FROM car WHERE rental_agreement_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, rentalAgreementId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return mapRow(rs);
        }
        return null;
    }

    // Gem en ny bil i databasen
    public void save(Car car) throws SQLException {
        Connection con = dataSource.getConnection();
        String sql = "INSERT INTO car (stelnumber, color, maker, model, year, status) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, car.getStelnumber());
        ps.setString(2, car.getColor());
        ps.setString(3, car.getMaker());
        ps.setString(4, car.getModel());
        ps.setInt(5, car.getYear());
        ps.setString(6, car.getStatus().name());
        ps.executeUpdate();
    }

    // Opdater en eksisterende bil
    public void update(Car car) throws SQLException {
        Connection con = dataSource.getConnection();
        String sql = "UPDATE car SET stelnumber = ?, color = ?, maker = ?, model = ?, year = ?, status = ? WHERE car_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, car.getStelnumber());
        ps.setString(2, car.getColor());
        ps.setString(3, car.getMaker());
        ps.setString(4, car.getModel());
        ps.setInt(5, car.getYear());
        ps.setString(6, car.getStatus().name());
        ps.setLong(7, car.getCarId());
        ps.executeUpdate();
    }

    // Slet en bil baseret på ID
    public void delete(Long id) throws SQLException {
        Connection con = dataSource.getConnection();
        String sql = "DELETE FROM car WHERE car_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        ps.executeUpdate();
    }

    // Mapper en række fra databasen til et Car objekt
    private Car mapRow(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setCarId(rs.getLong("car_id"));
        car.setStelnumber(rs.getString("stelnumber"));
        car.setColor(rs.getString("color"));
        car.setMaker(rs.getString("maker"));
        car.setModel(rs.getString("model"));
        car.setYear(rs.getInt("year"));
        car.setStatus(CarStatus.valueOf(rs.getString("status")));
        return car;
    }
}