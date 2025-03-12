package com.example.springboot_postgresql.model;

public class Address {

    private String addressLine;
    private String city;
    private String postalCode;
    private String country;

    // Getters
    public String getAddressLine() {
        return addressLine;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    // Setters
    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address [addressLine=" + addressLine + ", city=" + city + ", postalCode=" + postalCode
                + ", country=" + country + "]";
    }
    
}
