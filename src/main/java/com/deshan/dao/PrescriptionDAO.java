package com.deshan.dao;

import com.deshan.model.MedicalRecord;
import com.deshan.model.Patient;
import com.deshan.model.Prescription;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Data Access Object (DAO) class for managing Prescription entities.
public class PrescriptionDAO {
    private static final Logger LOGGER = Logger.getLogger(PrescriptionDAO.class.getName());

    // List to store all prescriptions
    private static List<Prescription> prescriptions = new ArrayList<>();

    // Retrieves all prescriptions.
    public static List<Prescription> getAllPrescriptions() {
        try {
            return prescriptions;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while retrieving all prescriptions", e);
            throw new RuntimeException("Error occurred while retrieving prescriptions", e);
        }
    }

    // Initializing prescriptions
    static {
        try {
            Patient patient1 = new Patient("Tharaka", "1234567", 1234, 31, "male", "colombo", "Critical");
            prescriptions.add(new Prescription(1, patient1, "Encorafenio", "0.9mg", "@ tablets per time", "every six hours"));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while initializing medical records: ", e);
            throw new RuntimeException("Error occurred while initializing medical records", e);
        }
    }

    // Retrieves a prescription by ID.
    public static Prescription getPrescriptionById(int id) {
        try {
            return prescriptions.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while retrieving prescription with ID " + id, e);
            throw new RuntimeException("Error occurred while retrieving prescription", e);
        }
    }

    // Adds a new prescription.
    public static boolean addPrescription(Prescription prescription) {
        try {
            return prescriptions.add(prescription);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while adding prescription", e);
            throw new RuntimeException("Error occurred while adding prescription", e);
        }
    }

    // Updates an existing prescription.
    public static boolean updatePrescription(Prescription prescription) {
        try {
            // Update logic
            return true; // Adding a return statement
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while updating prescription with ID " + prescription.getId(), e);
            throw new RuntimeException("Error occurred while updating prescription", e);
        }
    }

    // Deletes a prescription by ID.
    public static boolean deletePrescription(int id) {
        try {
            return prescriptions.removeIf(p -> p.getId() == id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while deleting prescription with ID " + id, e);
            throw new RuntimeException("Error occurred while deleting prescription", e);
        }
    }
}
