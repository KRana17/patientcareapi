package com.patientcare.wellnessdata.api.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.patientcare.wellnessdata.api.model.WellnessData;
import com.patientcare.wellnessdata.api.model.Patient;
import com.patientcare.wellnessdata.api.repository.WellnessDataRepository;
import com.patientcare.wellnessdata.api.repository.PatientRepository;
import com.patientcare.wellnessdata.api.restcontrollers.dto.WellnessDataRequest;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class WellnessController {

	private WellnessDataRepository repository;
	private PatientRepository patientRepository;

	@Autowired
	WellnessController(WellnessDataRepository repository, PatientRepository patientRepository) {
		this.repository = repository;
		this.patientRepository = patientRepository;
	}

	@RequestMapping(value = "/clinicals", method = RequestMethod.POST)
	public WellnessData saveClinicalData(@RequestBody WellnessDataRequest clinicalDataRequest) {
		Patient patient = patientRepository.findById(clinicalDataRequest.getPatientId()).get();
		WellnessData data = new WellnessData();
		data.setComponentName(clinicalDataRequest.getComponentName());
		data.setComponentValue(clinicalDataRequest.getComponentValue());
		data.setPatient(patient);
		return repository.save(data);
	}

	@RequestMapping(value = "/clinicals/{patientId}/{componentName}", method = RequestMethod.GET)
	public List<WellnessData> getClinicalData(@PathVariable("patientId") int patientId,
			@PathVariable("componentName") String componentName) {
		return repository.findByPatientIdAndComponentNameOrderByMeasuredDateTime(patientId, componentName);
	}

}
