package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.RentalAgreement;
import com.example.projekt3_gruppe_7.repository.RentalAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalAgreementService {

        private final RentalAgreementRepository rentalAgreementRepository;

@Autowired

    public RentalAgreementService (RentalAgreementRepository rentalAgreementRepository){
    this.rentalAgreementRepository = rentalAgreementRepository;
}   //validere at ingen felter er tomme eller null
    private boolean validateFields (RentalAgreement agreement){
    if (agreement.getCustomerName()== null || agreement.getCustomerName().isBlank())
        return false;

    if (agreement.getCustomerEmail()== null || agreement.getCustomerEmail().isBlank())
        return false;

    if (agreement.getChassisNumber() == null || agreement.getChassisNumber().isBlank())
        return false;

    if(agreement.getPickupLocation()== null || agreement.getPickupLocation().isBlank())
        return false;

    if (agreement.getStartDate()==null || agreement.getEndDate() == null)
        return false;

    if (agreement.getMonthlyPrice() <=0)
        return false;
    return true;
    }
    //validere at slutdato ikke er før start dato
    private boolean validateDates (RentalAgreement agreement){
    return agreement.getEndDate().isAfter(agreement.getStartDate());
    }

    //validere at bilen ikke allerede er lejet ud (checker via stelnummer)

    private boolean validateChassisIsNotRented (String chassisNumber){
    return !rentalAgreementRepository.isChassisNumberActivelyRented(chassisNumber);
    }

    // hovedmetode der kaldes fra controlleren
    // returnere true hvis aftalen blev oprettet, og false hvis noget fejlede

    public boolean create (RentalAgreement agreement){
    if (!validateFields (agreement))
        return false;

    if (!validateDates(agreement))
        return false;

    if (!validateChassisIsNotRented(agreement.getChassisNumber()))
        return false;

    rentalAgreementRepository.createRentalAgreement(agreement);
    return true;

    }
    public List<RentalAgreement> getAllAgreements() {
        return rentalAgreementRepository.getAllRentalAgreements();
    }
}
