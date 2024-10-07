package com.hospital.controllers;

import com.hospital.models.Appointment;
import com.hospital.services.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Отримання всіх призначень
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // Отримання призначення за ID
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Додавання нового призначення
    @PostMapping
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment newAppointment) {
        Appointment appointment = appointmentService.addAppointment(newAppointment);
        return ResponseEntity.status(201).body(appointment);
    }

    // Оновлення призначення
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAppointment(@PathVariable Long id, @RequestBody Appointment updatedAppointment) {
        appointmentService.updateAppointment(id, updatedAppointment);
        return ResponseEntity.noContent().build();
    }

    // Видалення призначення
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
