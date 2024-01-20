package com.patientcare.wellnessdata.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patientcare.wellnessdata.api.model.WellnessData;

public interface WellnessDataRepository extends JpaRepository<WellnessData, Integer> {

	List<WellnessData> findByPatientIdAndComponentNameOrderByMeasuredDateTime(int patientId, String componentName);

}
