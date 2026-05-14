package com.example.projekt3_gruppe_7.model;

import java.time.LocalDate;

public class RentalAgreement {
    private Long rentalagreementId;
    private Long carId;
    private Long costumerId;
    private Long damageReportId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Location location;
    private SubscriptionType subscriptionType;
    private double monthlyPrice;

    // fuld constructer
    public RentalAgreement(Long rentalagreementId, Long carId, Long costumerId, Long damageReportId, LocalDate startDate, LocalDate endDate, Location location, SubscriptionType subscriptionType,double monthlyPrice) {
        this.rentalagreementId = rentalagreementId;
        this.carId = carId;
        this.costumerId = costumerId;
        this.damageReportId = damageReportId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.subscriptionType = subscriptionType;
        this.monthlyPrice = monthlyPrice;
    }
    // Contructer uden rental id
    public RentalAgreement(Long carId, LocalDate startDate, LocalDate endDate, Location location, SubscriptionType subscriptionType, double monthlyPrice) {
        this.carId = carId;
        this.damageReportId = damageReportId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.subscriptionType = subscriptionType;
        this.monthlyPrice = monthlyPrice;
    }
    //Constructer uden rental Id og damageReport
    public RentalAgreement(Long carId, Long costumerId, LocalDate startDate, LocalDate endDate, Location location, SubscriptionType subscriptionType,double monthlyPrice) {
        this.carId = carId;
        this.costumerId = costumerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.subscriptionType = subscriptionType;
        this.monthlyPrice = monthlyPrice;
    }

    public RentalAgreement() {
    }

    public Long getRentalagreementId() {
        return rentalagreementId;
    }

    public Long getCarId() {
        return carId;
    }

    public Long getCustomerId() {
        return costumerId;
    }

    public Long getDamageReportId() {
        return damageReportId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Location getLocation() {
        return location;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }


    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
    private void setCarId(Long carId) {
        this.carId = carId;
    }

    private void setCustomerId(Long costumerId) {
        this.costumerId = costumerId;
    }

    private void setRentalagreementId(Long rentalagreementId) {
        this.rentalagreementId = rentalagreementId;
    }

    public double getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(double monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }
}
