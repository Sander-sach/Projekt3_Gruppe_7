package com.example.projekt3_gruppe_7.repository;

import com.example.projekt3_gruppe_7.model.Car;
import com.example.projekt3_gruppe_7.model.CarStatus;
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
public class CarRepositoryImpl implements CarRepository {

    private final DataSource dataSource;

    @Autowired
    public CarRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Hent én bil baseret på ID
    public Car findById(Long id) {
        String sql = "SELECT * FROM car WHERE car_id = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Hent alle biler
    public List<Car> findAll() {
        List<Car> list = new ArrayList<>();
        String sql = "SELECT * FROM car";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Hent bil via lejeaftale-ID (bruges til at opdatere bilstatus)
    public Car findByRentalAgreementId(Long rentalAgreementId) {
        String sql = "SELECT car.* FROM car\n"+
                " JOIN rental_agreement ON car.car_id = rental_agreement.car_id\n" +
                "WHERE rental_agreement.agreement_id = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, rentalAgreementId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Gem en ny bil i databasen
    public void save(Car car) {
        String sql = "INSERT INTO car (stel_number, colour, maker, model, year, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, car.getStelnumber());
            ps.setString(2, car.getColor());
            ps.setString(3, car.getMaker());
            ps.setString(4, car.getModel());
            ps.setInt(5, car.getYear());
            ps.setString(6, car.getStatus().name());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Opdater en eksisterende bil
    public void update(Car car) {
        String sql = "UPDATE car SET stel_number = ?, colour = ?, maker = ?, model = ?, year = ?, status = ? WHERE car_id = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, car.getStelnumber());
            ps.setString(2, car.getColor());
            ps.setString(3, car.getMaker());
            ps.setString(4, car.getModel());
            ps.setInt(5, car.getYear());
            ps.setString(6, car.getStatus().name());
            ps.setLong(7, car.getCarId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Slet en bil baseret på ID
    public void delete(Long id) {
        String sql = "DELETE FROM car WHERE car_id = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mapper en række fra databasen til et Car objekt
    private Car mapRow(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setCarId(rs.getLong("car_id"));
        car.setStelnumber(rs.getString("stel_number"));
        car.setColor(rs.getString("colour"));
        car.setMaker(rs.getString("maker"));
        car.setModel(rs.getString("model"));
        car.setYear(rs.getInt("year"));
        car.setStatus(CarStatus.valueOf(rs.getString("status")));
        return car;
    }
    public Car findByStelNumber(String stelNumber){
        String sql = "SELECT * FROM car WHERE stel_number = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, stelNumber);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Hent alle udlejede biler
    public List<Car> findAllRented() {
        List<Car> list = new ArrayList<>();
        String sql = "SELECT * FROM car WHERE status = 'RENTED'";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Hent samlet månedlig pris på alle udlejede biler
    public double getTotalRentedPrice() {
        String sql = "SELECT SUM(ra.monthly_price) FROM rental_agreement ra " +
                "JOIN car c ON ra.car_id = c.car_id " +
                "WHERE c.status = 'RENTED'";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    }