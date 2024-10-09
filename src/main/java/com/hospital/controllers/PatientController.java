package com.hospital.controllers;

import com.hospital.models.Patient;
import com.hospital.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient newPatient) {
        patientService.addPatient(newPatient);
        return ResponseEntity.status(201).body(newPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        patientService.updatePatient(id, updatedPatient);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
