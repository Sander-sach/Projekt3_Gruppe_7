package com.example.projekt3_gruppe_7.repository;

import com.example.projekt3_gruppe_7.model.DamageItem;

import java.sql.SQLException;
import java.util.List;

public interface DamageItemRepository extends BaseRepository<DamageItem>{
    DamageItem findById(Long id);

    List<DamageItem> findAll()throws SQLException;

    void save(DamageItem entity);

    void update(DamageItem entity);

    void delete(Long id)throws SQLException;

    void saveAll(List<DamageItem> items, Long reportId);
}
