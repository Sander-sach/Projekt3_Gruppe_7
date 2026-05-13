package com.example.projekt3_gruppe_7.repository;

import java.sql.SQLException;
import java.util.List;

public interface BaseRepository<T> {

    T findById(Long id) throws SQLException;

    List<T> findAll() throws SQLException;

    void save(T entity) throws  SQLException;

    void update(T entity) throws SQLException;

    void delete(Long id) throws SQLException;
}
