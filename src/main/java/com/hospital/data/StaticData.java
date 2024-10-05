package com.hospital.data;

import com.hospital.model.Patient;
import java.util.ArrayList;
import java.util.List;

public class StaticData {
    private static List<Patient> patients = new ArrayList<>();

    static {
        patients.add(new Patient("1", "John Doe", "Flu"));
        patients.add(new Patient("2", "Jane Doe", "Cold"));
    }

    public static List<Patient> getPatients() {
        return patients;
    }
}
