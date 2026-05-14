package com.example.projekt3_gruppe_7.repository;

import com.example.projekt3_gruppe_7.model.RentalAgreement;
import java.util.List;

public interface RentalAgreementRepository extends BaseRepository<RentalAgreement> {
    RentalAgreement findById(Long id);
    List<RentalAgreement> findAll();
    void save(RentalAgreement entity);
    void update(RentalAgreement entity);
    void delete(Long id);
    List<RentalAgreement> findRentalAgreementMissingRegistration();
}