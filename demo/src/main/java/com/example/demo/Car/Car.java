package com.example.demo.Car;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Car {
    @Id
    @SequenceGenerator(
            name="car_sequence",
            sequenceName = "car_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "car_sequence"
    )
    private Long id;

    private String brand;

    private String model;

    private String year;

    private String bodyPaint;

    private String bodyType;

    private String fuelType;

    private Integer numberOfSeats;

    private Double price;

    private Integer numberOfDoors;

    private String warrantyDuration;

    private Integer width;

    private Integer height;

    private Integer length;

    private Integer fuelTankCapacity;

    private Integer maxSpeed;

    private String acceleration;

    private String fuelConsumption;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Equipment> equipment;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Engine engine;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Transmission transmission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBodyPaint() {
        return bodyPaint;
    }

    public void setBodyPaint(String bodyPaint) {
        this.bodyPaint = bodyPaint;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numOfSeats) {
        this.numberOfSeats = numOfSeats;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public String getWarrantyDuration() {
        return warrantyDuration;
    }

    public void setWarrantyDuration(String warrantyDuration) {
        this.warrantyDuration = warrantyDuration;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(Integer fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(String acceleration) {
        this.acceleration = acceleration;
    }

    public String getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(String fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Car(Long id, String brand, String model, String year, String bodyPaint, String bodyType, String fuelType, Integer numberOfSeats, Double price, Integer numberOfDoors, String warrantyDuration, Integer width, Integer height, Integer length, Integer fuelTankCapacity, Integer maxSpeed, String acceleration, String fuelConsumption, List<Equipment> equipment, Engine engine, Transmission transmission) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.bodyPaint = bodyPaint;
        this.bodyType = bodyType;
        this.fuelType = fuelType;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
        this.numberOfDoors = numberOfDoors;
        this.warrantyDuration = warrantyDuration;
        this.width = width;
        this.height = height;
        this.length = length;
        this.fuelTankCapacity = fuelTankCapacity;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.fuelConsumption = fuelConsumption;
        this.equipment = equipment;
        this.engine = engine;
        this.transmission = transmission;
    }

    public Car() {
    }
}
