package com.example.demo.Car;

import jakarta.persistence.*;

@Entity
@Table
public class Equipment {
    @Id
    @SequenceGenerator(
            name = "Equipement_sequence",
            sequenceName = "Equipement_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Equipement_sequence"
    )
    private Long id;

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    public Equipment() {
    }

    public Equipment(Long id, String name, String description, Car car) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}