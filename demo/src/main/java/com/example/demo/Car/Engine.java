package com.example.demo.Car;

import jakarta.persistence.*;

@Entity
@Table
public class Engine {
    @Id
    @SequenceGenerator(
            name = "Engine_sequence",
            sequenceName = "Engine_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Engine_sequence"
    )
    private Long id;

    private Double numOfCylinders;

    private String fuelType;

    private Double engineSize;

    private String torque;

    private Integer horsepower;



    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    public Engine(Long id, Double numOfCylinders, String fuelType, Double engineSize, String torque, Integer horsepower, Car car) {
        this.id = id;

        this.numOfCylinders = numOfCylinders;
        this.fuelType = fuelType;
        this.engineSize = engineSize;
        this.torque = torque;
        this.horsepower = horsepower;
        this.car = car;
    }

    public Engine() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNumOfCylinders() {
        return numOfCylinders;
    }

    public void setNumOfCylinders(Double numOfCylinders) {
        this.numOfCylinders = numOfCylinders;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(Double engineSize) {
        this.engineSize = engineSize;
    }

    public String getTorque() {
        return torque;
    }

    public void setTorque(String torque) {
        this.torque = torque;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
