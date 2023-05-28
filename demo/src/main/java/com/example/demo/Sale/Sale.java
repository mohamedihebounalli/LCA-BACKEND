package com.example.demo.Sale;

import jakarta.persistence.*;

@Entity
@Table
public class Sale {
    @Id
    @SequenceGenerator(
            name="sale_sequence",
            sequenceName = "sale_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sale_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String manufacturer ;
    private String carColor ;
    private String  paymentMethod;
    private String price;
    private String ownerCIN;
    public String getOwnerCIN() {
        return ownerCIN;
    }

    public void setOwnerCIN(String ownerCIN) {
        this.ownerCIN = ownerCIN;
    }



    public Sale() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Sale(Long id, String firstName, String lastName, String manufacturer, String carColor, String paymentMethod, String price,String ownerCIN) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.manufacturer = manufacturer;
        this.carColor = carColor;
        this.paymentMethod = paymentMethod;
        this.price = price;
        this.ownerCIN=ownerCIN;
    }
}
