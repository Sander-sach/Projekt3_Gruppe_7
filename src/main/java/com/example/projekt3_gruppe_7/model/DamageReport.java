package com.example.projekt3_gruppe_7.model;

import java.time.LocalDate;

public class DamageReport {
    private Long damageReportId;
    private Long carId;
    private LocalDate reportDate;
    //fuld constructer
    public DamageReport(Long damageReportId, Long carId, LocalDate reportDate) {
        damageReportId = damageReportId;
        this.carId = carId;
        this.reportDate = reportDate;
    }
    //constructer til save/creation
    public DamageReport(Long carId, LocalDate reportDate) {
        this.carId = carId;
        this.reportDate = reportDate;
    }

    public Long getDamageReportId() {
        return damageReportId;
    }

    public Long getCarId() {
        return carId;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setDamageReportId(Long damageReportId) {
        damageReportId = damageReportId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }
}
