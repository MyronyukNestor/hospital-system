package com.hospital.controller;

import com.hospital.data.StaticData;
import com.hospital.model.Patient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return StaticData.getPatients();
    }
}
