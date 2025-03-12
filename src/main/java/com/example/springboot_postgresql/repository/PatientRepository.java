package com.example.springboot_postgresql.repository;


import java.util.List;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.springboot_postgresql.model.Address;
import com.example.springboot_postgresql.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
  List<Patient> findByName(String name);
  List<Patient> findByPhonenumber(String phonenumber);
  List<Patient> findByGender(String gender); 
  List<Patient> findByBirthdate(LocalDate birthdate);
  List<Patient> findByAddress(Address address);
}

