package com.hospital.services;

import com.hospital.models.Doctor;
import com.hospital.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Отримання всіх лікарів
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Отримання лікаря за ID
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    // Додавання нового лікаря
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Оновлення лікаря
    public void updateDoctor(Long id, Doctor updatedDoctor) {
        Optional<Doctor> existingDoctor = getDoctorById(id);
        existingDoctor.ifPresent(doctor -> {
            doctor.setName(updatedDoctor.getName());
            doctor.setSpecialization(updatedDoctor.getSpecialization());
            doctorRepository.save(doctor);
        });
    }

    // Видалення лікаря
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
