package com.example.demo.AfterSaleServiceRequest;

import jakarta.persistence.*;

@Entity
@Table
public class AfterSaleServiceRequest {
    @Id
    @SequenceGenerator(
            name="request_sequence",
            sequenceName = "request_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "request_sequence"
    )
    private Long id;
    private String CIN;
    private String firstName;
    private String lastName;
    private String manufacturer;
    private String registrationNumber;
    private String description;
    private String status="Pending";


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AfterSaleServiceRequest() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String model) {
        this.manufacturer = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String immatriculation) {
        this.registrationNumber = immatriculation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AfterSaleServiceRequest(Long id, String CIN, String firstName, String lastName, String manufacturer, String registrationNumber, String description) {
        this.id = id;
        this.CIN = CIN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.manufacturer = manufacturer;
        this.registrationNumber = registrationNumber;
        this.description = description;
    }
}
