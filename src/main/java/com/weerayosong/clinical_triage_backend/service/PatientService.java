package com.weerayosong.clinical_triage_backend.service;

import com.weerayosong.clinical_triage_backend.model.Patient;
import com.weerayosong.clinical_triage_backend.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    // Constructor Injection for Repository
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Retrieve all active patients sorted by status and time
    public List<Patient> getAllPatients() {
        return patientRepository.findAllByOrderByStatusAscRegisteredAtAsc();
    }

    // Save a new patient to the database
    public Patient registerPatient(@NonNull Patient patient) {
        return patientRepository.save(patient);
    }

    // Update patient status with basic validation
    public Patient updatePatientStatus(@NonNull Long id, @NonNull Patient.Status newStatus) {
        Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + id));
            
        patient.setStatus(newStatus);
        return patientRepository.save(patient);
    }

    // Delete
    public void deletePatient(@NonNull Long id) {
    patientRepository.deleteById(id);
}
}