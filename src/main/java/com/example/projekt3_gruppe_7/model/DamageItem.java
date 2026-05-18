package com.example.projekt3_gruppe_7.model;

public class DamageItem {
    private Long itemId;
    private Long damageReportId;
    private String description;
    private double price;

    //fuld constructer
    public DamageItem(Long itemId, Long damageReportId, String description, double price) {
        this.itemId = itemId;
        this.damageReportId = damageReportId;
        this.description = description;
        this.price = price;
    }
    //constructer til save/creation
    public DamageItem(Long damageReportId, String description, double price) {
        this.damageReportId = damageReportId;
        this.description = description;
        this.price = price;
    }

    public Long getItemId() {
        return itemId;
    }

    public Long getDamageReportId() {
        return damageReportId;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public void setDamageReportId(Long damageReportId) {
        this.damageReportId = damageReportId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
