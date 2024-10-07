package com.hospital.services;

import com.hospital.models.Appointment;
import com.hospital.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // Отримання всіх призначень
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Отримання призначення за ID
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    // Додавання нового призначення
    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Оновлення призначення
    public void updateAppointment(Long id, Appointment updatedAppointment) {
        Optional<Appointment> existingAppointment = getAppointmentById(id);
        existingAppointment.ifPresent(appointment -> {
            appointment.setType(updatedAppointment.getType());
            appointment.setDescription(updatedAppointment.getDescription());
            appointmentRepository.save(appointment);
        });
    }

    // Видалення призначення
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
