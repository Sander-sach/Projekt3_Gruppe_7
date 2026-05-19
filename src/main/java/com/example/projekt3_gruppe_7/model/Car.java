package com.example.projekt3_gruppe_7.model;


public class Car {

    private Long carId;
    private String stelnumber;
    private String color;
    private String maker;
    private String model;
    private int year;
    private CarStatus status;

    // Default constructor
    public Car() {}

    // All-args constructor
    public Car(Long carId, String stelnumber, String color, String maker, String model, int year, CarStatus status) {
        this.carId = carId;
        this.stelnumber = stelnumber;
        this.color = color;
        this.maker = maker;
        this.model = model;
        this.year = year;
        this.status = status;

    }

    public Car(String stelnumber, String color, String maker, String model, int year, CarStatus status) {
        this.stelnumber = stelnumber;
        this.color = color;
        this.maker = maker;
        this.model = model;
        this.year = year;
        this.status = status;
    }

    // Getters og Setters
    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getStelnumber() {
        return stelnumber;
    }

    public void setStelnumber(String stelnumber) {
        this.stelnumber = stelnumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", stelnumber='" + stelnumber + '\'' +
                ", color='" + color + '\'' +
                ", maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", status=" + status +
                '}';
    }
}
