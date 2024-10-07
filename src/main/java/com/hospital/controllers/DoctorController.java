package com.hospital.controllers;

import com.hospital.models.Doctor;
import com.hospital.services.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Отримання всіх лікарів
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // Отримання лікаря за ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Додавання нового лікаря
    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor newDoctor) {
        Doctor doctor = doctorService.addDoctor(newDoctor);
        return ResponseEntity.status(201).body(doctor);
    }

    // Оновлення лікаря
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDoctor(@PathVariable Long id, @RequestBody Doctor updatedDoctor) {
        doctorService.updateDoctor(id, updatedDoctor);
        return ResponseEntity.noContent().build();
    }

    // Видалення лікаря
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
