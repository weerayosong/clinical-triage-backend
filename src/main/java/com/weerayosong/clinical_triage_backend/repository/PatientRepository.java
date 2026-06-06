package com.weerayosong.clinical_triage_backend.repository;

import com.weerayosong.clinical_triage_backend.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllByOrderByStatusAscRegisteredAtAsc();
}