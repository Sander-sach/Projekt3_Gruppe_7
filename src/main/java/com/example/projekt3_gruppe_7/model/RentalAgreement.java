package com.example.projekt3_gruppe_7.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class RentalAgreement {

    private Long rentalAgreementId;
    private String customerName;
    private String customerEmail;
    private String chassisNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private double monthlyPrice;
    private String pickupLocation;

    public RentalAgreement() {
    }
        public RentalAgreement(String customerName, String customerEmail, String chassisNumber,
                LocalDate startDate, LocalDate endDate,
        double monthlyPrice, String pickupLocation){

        this.customerName=customerName;
        this.customerEmail=customerEmail;
        this.chassisNumber=chassisNumber;
        this.startDate=startDate;
        this.endDate=endDate;
        this.monthlyPrice=monthlyPrice;
        this.pickupLocation=pickupLocation;
    }

    public RentalAgreement(Long rentalAgreementId, String customerName, String customerEmail,
                           String chassisNumber, LocalDate startDate, LocalDate endDate,
                           double monthlyPrice, String pickupLocation) {
        this.rentalAgreementId=rentalAgreementId;
        this.customerName=customerName;
        this.customerEmail=customerEmail;
        this.chassisNumber=chassisNumber;
        this.startDate=startDate;
        this.endDate=endDate;
        this.monthlyPrice=monthlyPrice;
        this.pickupLocation=pickupLocation;

    }

    public Long getRentalAgreementId(){
        return rentalAgreementId;
    }
    public String getCustomerName(){
        return customerName;
    }
    public String getCustomerEmail(){
        return customerEmail;
    }
    public String getChassisNumber(){
        return chassisNumber;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

    public Double getMonthlyPrice(){
        return monthlyPrice;
    }
    public String getPickupLocation(){
        return pickupLocation;
    }

    public void setRentalAgreementId(Long rentalAgreementId){
        this.rentalAgreementId=rentalAgreementId;
    }
    public void setCustomerName(String customerName){
        this.customerName=customerName;
    }

    public void setCustomerEmail(String customerEmail){
        this.customerEmail=customerEmail;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }
    public void setStartDate(LocalDate startDate){
        this.startDate=startDate;
    }
    public void setEndDate(LocalDate endDate){
        this.endDate=endDate;
    }

    public void setMonthlyPrice(Double monthlyPrice){
        this.monthlyPrice=monthlyPrice;
    }

    public void setPickupLocation(String pickupLocation){
        this.pickupLocation=pickupLocation;
    }
}
