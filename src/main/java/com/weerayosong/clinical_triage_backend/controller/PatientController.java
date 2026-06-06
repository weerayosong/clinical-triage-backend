package com.weerayosong.clinical_triage_backend.controller;

import com.weerayosong.clinical_triage_backend.model.Patient;
import com.weerayosong.clinical_triage_backend.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.lang.NonNull;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular frontend access
public class PatientController {

    private final PatientService patientService;

    // Constructor Injection for Service
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // GET: /api/v1/patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    // POST: /api/v1/patients
    @PostMapping
    public ResponseEntity<Patient> registerPatient(@NonNull @RequestBody Patient patient) {
        Patient savedPatient = patientService.registerPatient(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    // PATCH: /api/v1/patients/{id}/status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Patient> updatePatientStatus(
            @NonNull @PathVariable Long id, 
            @NonNull @RequestParam Patient.Status status) {
        try {
            Patient updatedPatient = patientService.updatePatientStatus(id, status);
            return ResponseEntity.ok(updatedPatient);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
public ResponseEntity<Void> deletePatient(@NonNull @PathVariable Long id) {
    patientService.deletePatient(id);
    return ResponseEntity.noContent().build();
}
}