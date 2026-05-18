package com.example.projekt3_gruppe_7.model;

public class CarRegistration {

    private Long carRegistrationId;
    private String leasingCode;
    private String IRKCode;
    private String plateNumber;
    private Long rentalAgreementId;

    // Default constructor
    public CarRegistration() {}

    // All-args constructor
    public CarRegistration(Long carRegistrationId, String leasingCode, String IRKCode, String plateNumber, Long rentalAgreementId) {
        this.carRegistrationId = carRegistrationId;
        this.leasingCode = leasingCode;
        this.IRKCode = IRKCode;
        this.plateNumber = plateNumber;
        this.rentalAgreementId = rentalAgreementId;
    }
    // none id constructer
    public CarRegistration(String leasingCode, String IRKCode, String plateNumber,Long rentalAgreementId) {
        this.leasingCode = leasingCode;
        this.IRKCode = IRKCode;
        this.plateNumber = plateNumber;
        this.rentalAgreementId = rentalAgreementId;
    }

    // Getters and Setters
    public Long getCarRegistrationId() {
        return carRegistrationId;
    }

    public void setCarRegistrationId(Long carRegistrationId) {
        this.carRegistrationId = carRegistrationId;
    }

    public String getLeasingCode() {
        return leasingCode;
    }

    public void setLeasingCode(String leasingCode) {
        this.leasingCode = leasingCode;
    }

    public String getIRKCode() {
        return IRKCode;
    }

    public void setIRKCode(String IRKCode) {
        this.IRKCode = IRKCode;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Long getRentalAgreementId() {
        return rentalAgreementId;
    }

    public void setRentalAgreementId(Long rentalAgreementId) {
        this.rentalAgreementId = rentalAgreementId;
    }

    @Override
    public String toString() {
        return "CarRegistration{" +
                "carRegistrationId=" + carRegistrationId +
                ", leasingCode='" + leasingCode + '\'' +
                ", IRKCode='" + IRKCode + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", rentalAgreementId=" + rentalAgreementId +
                '}';
    }
}
