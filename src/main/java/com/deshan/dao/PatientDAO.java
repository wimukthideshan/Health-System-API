package com.deshan.dao;

import com.deshan.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

// Data Access Object (DAO) class for managing Patient entities.
public class PatientDAO {
    private static final Logger logger = Logger.getLogger(PatientDAO.class.getName());

    // List to store all patients
    private static List<Patient> patients = new ArrayList<>();

    // Retrieves all patients.
    public static List<Patient> getAllPatients() {
        try {
            return patients;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all patients", e);
            throw new RuntimeException("Error occurred while retrieving patients", e);
        }
    }

    // Initializing patients
    static {
        try {
            patients.add(new Patient("Tharaka", "1234567", 1234, 31, "male", "colombo", "Critical"));
            patients.add(new Patient("Sayuri", "1234557", 123, 23, "female", "colombo", "Good"));
            patients.add(new Patient("Mayuri", "12344567", 12, 30, "female", "colombo", "Good"));
            patients.add(new Patient("Sasmitha", "1234gr567", 23, 31, "male", "colombo", "Need Inspections"));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while initializing patients", e);
            throw new RuntimeException("Error occurred while initializing patients", e);
        }
    }

    // Retrieves a patient by ID.
    public static Patient getPatientById(int id) {
        try {
            return patients.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving patient with ID " + id, e);
            throw new RuntimeException("Error occurred while retrieving patient", e);
        }
    }

    // Adds a new patient.
    public static boolean addPatient(Patient patient) {
        try {
            return patients.add(patient);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while adding patient", e);
            throw new RuntimeException("Error occurred while adding patient", e);
        }
    }

    // Updates an existing patient.
    public static boolean updatePatient(Patient patient) {
        try {
            Patient existingPatient = getPatientById(patient.getId());
            if (existingPatient != null) {
                patients = patients.stream()
                        .map(p -> p.getId() == patient.getId() ? patient : p)
                        .collect(Collectors.toList());
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating patient with ID " + patient.getId(), e);
            throw new RuntimeException("Error occurred while updating patient", e);
        }
    }

    // Deletes a patient by ID.
    public static boolean deletePatient(int id) {
        try {
            return patients.removeIf(p -> p.getId() == id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting patient with ID " + id, e);
            throw new RuntimeException("Error occurred while deleting patient", e);
        }
    }
}
