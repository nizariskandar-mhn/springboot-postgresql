package com.example.springboot_postgresql.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_postgresql.model.Patient;
import com.example.springboot_postgresql.repository.PatientRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PatientController {

  @Autowired
  PatientRepository patientRepository;

  // Get all patients or search by name
  @GetMapping("/patients")
  public ResponseEntity<List<Patient>> getAllPatients(@RequestParam(required = false) String name) {
    try {
      List<Patient> patients = new ArrayList<>();

      if (name == null)
        patientRepository.findAll().forEach(patients::add);
      else
        patientRepository.findByName(name).forEach(patients::add);

      if (patients.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(patients, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // Get patient by ID
  @GetMapping("/patients/{id}")
  public ResponseEntity<Patient> getPatientById(@PathVariable("id") long id) {
    Optional<Patient> patientData = patientRepository.findById(id);

    if (patientData.isPresent()) {
      return new ResponseEntity<>(patientData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Create a new patient
  @PostMapping("/patients")
  public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
    try {
      Patient _patient = patientRepository.save(new Patient(
        patient.getName(),
        patient.getPhonenumber(),
        patient.getGender(),
        patient.getBirthdate(),
        patient.getAddress()
      ));
      return new ResponseEntity<>(_patient, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // Update patient information
  @PutMapping("/patients/{id}")
  public ResponseEntity<Patient> updatePatient(@PathVariable("id") long id, @RequestBody Patient patient) {
    Optional<Patient> patientData = patientRepository.findById(id);

    if (patientData.isPresent()) {
      Patient _patient = patientData.get();
      _patient.setName(patient.getName());
      _patient.setPhonenumber(patient.getPhonenumber());
      _patient.setGender(patient.getGender());
      _patient.setBirthdate(patient.getBirthdate());
      _patient.setAddress(patient.getAddress());
      return new ResponseEntity<>(patientRepository.save(_patient), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Delete a patient by ID
  @DeleteMapping("/patients/{id}")
  public ResponseEntity<HttpStatus> deletePatient(@PathVariable("id") long id) {
    try {
      patientRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // Delete all patients
  @DeleteMapping("/patients")
  public ResponseEntity<HttpStatus> deleteAllPatients() {
    try {
      patientRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // Find patients by gender
  @GetMapping("/patients/gender/{gender}")
  public ResponseEntity<List<Patient>> findByGender(@PathVariable("gender") String gender) {
    try {
      List<Patient> patients = patientRepository.findByGender(gender);

      if (patients.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(patients, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
