package com.hospital.service;

import com.hospital.data.StaticData;
import com.hospital.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    // Отримання всіх пацієнтів
    public List<Patient> getAllPatients() {
        return StaticData.getPatients();
    }

    // Отримання пацієнта за ID
    public Optional<Patient> getPatientById(String id) {
        return StaticData.getPatients().stream()
                .filter(patient -> patient.getId().equals(id))
                .findFirst();
    }

    // Додавання нового пацієнта
    public void addPatient(Patient newPatient) {
        StaticData.getPatients().add(newPatient);
    }

    // Оновлення даних пацієнта
    public void updatePatient(String id, Patient updatedPatient) {
        getPatientById(id).ifPresent(patient -> {
            patient.setName(updatedPatient.getName());
            patient.setDisease(updatedPatient.getDisease());
        });
    }

    // Видалення пацієнта за ID
    public void deletePatient(String id) {
        StaticData.getPatients().removeIf(patient -> patient.getId().equals(id));
    }
}
