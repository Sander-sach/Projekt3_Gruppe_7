package com.example.projekt3_gruppe_7.repository;

import java.sql.SQLException;
import java.util.List;

public interface BaseRepository<T> {

    T findById(Long id);

    List<T> findAll()throws SQLException;

    void save(T entity);

    void update(T entity);

    void delete(Long id)throws SQLException;
}
