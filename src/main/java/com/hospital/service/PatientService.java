package com.hospital.service;

import com.hospital.model.Patient;
import com.hospital.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Отримання всіх пацієнтів
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Отримання пацієнта за ID
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    // Додавання нового пацієнта
    public void addPatient(Patient newPatient) {
        patientRepository.save(newPatient);
    }

    // Оновлення даних пацієнта
    public void updatePatient(Long id, Patient updatedPatient) {
        patientRepository.findById(id).ifPresent(patient -> {
            patient.setName(updatedPatient.getName());
            patient.setDisease(updatedPatient.getDisease());
            patientRepository.save(patient);
        });
    }

    // Видалення пацієнта за ID
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
