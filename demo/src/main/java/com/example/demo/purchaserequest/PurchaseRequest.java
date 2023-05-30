package com.example.demo.purchaserequest;

import jakarta.persistence.*;

@Entity
@Table
public class PurchaseRequest {
    @Id
    @SequenceGenerator(
            name="purchase_sequence",
            sequenceName = "purchase_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "purchase_sequence"
    )
    private Long id;
    private String ownerCIN;
    private String firstName;
    private String lastName;
    private String model;
    private String email;
    private String address;
    private String phoneNumber;

    public PurchaseRequest(Long id, String ownerCIN, String firstName, String lastName, String model, String email, String address, String phoneNumber) {
        this.id = id;
        this.ownerCIN = ownerCIN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.model = model;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public PurchaseRequest() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerCIN() {
        return ownerCIN;
    }

    public void setOwnerCIN(String ownerCIN) {
        this.ownerCIN = ownerCIN;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
