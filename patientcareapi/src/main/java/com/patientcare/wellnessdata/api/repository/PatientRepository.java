package com.patientcare.wellnessdata.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patientcare.wellnessdata.api.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

	List<Patient> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

}
