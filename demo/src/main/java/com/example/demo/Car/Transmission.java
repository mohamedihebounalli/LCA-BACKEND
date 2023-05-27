package com.example.demo.Car;

import jakarta.persistence.*;



@Entity
@Table
public class Transmission {
    @Id
    @SequenceGenerator(
            name = "Transmission_sequence",
            sequenceName = "Transmission_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Transmission_sequence"
    )
    private Long id;



    private String gearboxType;

    private String transmissionType;

    private Integer numOfGears;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    public Transmission(Long id, String gearboxType, String transmissionType, Integer numOfGears, Car car) {
        this.id = id;

        this.gearboxType = gearboxType;
        this.transmissionType = transmissionType;
        this.numOfGears = numOfGears;
        this.car = car;
    }

    public Transmission() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGearboxType() {
        return gearboxType;
    }

    public void setGearboxType(String gearboxType) {
        this.gearboxType = gearboxType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Integer getNumOfGears() {
        return numOfGears;
    }

    public void setNumOfGears(Integer numOfGears) {
        this.numOfGears = numOfGears;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}