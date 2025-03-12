package com.example.springboot_postgresql.model;

import jakarta.persistence.*; // For Spring Boot 3 JPA
import java.time.LocalDate;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Automatically generated primary key
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phonenumber")
    private String phonenumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Embedded  // If Address is an embedded object in the same table
    private Address address;

    public Patient() {
    }

    public Patient(String name, String phonenumber, String gender, LocalDate birthdate, Address address) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.setGender(gender);
        this.birthdate = birthdate;
        this.address = address;
    }

    // Getters and Setters 
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender != null) {
            if (gender.equalsIgnoreCase("M")) {
                this.gender = "male";
            } else if (gender.equalsIgnoreCase("F")) {
                this.gender = "female";
            } else {
                this.gender = gender;
            }
        }
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Patient [id=" + id + ", name=" + name + ", phonenumber=" + phonenumber + ", gender=" + gender
                + ", birthdate=" + birthdate + ", address=" + address + "]";
    }
}
