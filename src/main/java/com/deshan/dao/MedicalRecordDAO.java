package com.deshan.dao;

import com.deshan.model.MedicalRecord;
import com.deshan.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Level;
import java.util.logging.Logger;

// Data Access Object (DAO) class for managing MedicalRecord entities.
public class MedicalRecordDAO {
    private static final Logger logger = Logger.getLogger(MedicalRecordDAO.class.getName());

    // List to store all medical records
    private static List<MedicalRecord> medicalRecords = new ArrayList<>();

    // Retrieves all medical records.
    public static List<MedicalRecord> getAllMedicalRecords() {
        try {
            return medicalRecords;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all medical records: ", e);
            throw new RuntimeException("Error occurred while retrieving medical records", e);
        }
    }

    // Initializing medical records
    static {
        try {
            Patient patient1 = new Patient("Tharaka", "1234567", 1234, 31, "male", "colombo", "Critical");
            Patient patient2 = new Patient("Sayuri", "1234557", 123, 23, "female", "colombo", "Good");
            Patient patient3 = new Patient("Mayuri", "12344567", 12, 30, "female", "colombo", "Good");
            Patient patient4 = new Patient("Sasmitha", "1234gr567", 23, 31, "male", "colombo", "Need Inspections");

            medicalRecords.add(new MedicalRecord(1, patient1, "cancer", "chemotherapy", "Stage 4"));
            medicalRecords.add(new MedicalRecord(2, patient2, "common flu", "syrup", "none"));
            medicalRecords.add(new MedicalRecord(3, patient3, "bone fracture", "plastering", "broken ulna"));
            medicalRecords.add(new MedicalRecord(4, patient4, "covid19", "Remdesivir", "asthma"));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while initializing medical records: ", e);
            throw new RuntimeException("Error occurred while initializing medical records", e);
        }
    }

    // Retrieves a medical record by ID.
    public static MedicalRecord getMedicalRecordById(int id) {
        try {
            return medicalRecords.stream()
                    .filter(mr -> mr.getId() == id)
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving medical record with ID " + id + ": ", e);
            throw new RuntimeException("Error occurred while retrieving medical record", e);
        }
    }

    // Adds a new medical record.
    public static boolean addMedicalRecord(MedicalRecord medicalRecord) {
        try {
            return medicalRecords.add(medicalRecord);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while adding medical record: ", e);
            throw new RuntimeException("Error occurred while adding medical record", e);
        }
    }

    // Updates an existing medical record.
    public static boolean updateMedicalRecord(MedicalRecord medicalRecord) {
        try {
            MedicalRecord existingMedicalRecord = getMedicalRecordById(medicalRecord.getId());
            if (existingMedicalRecord != null) {
                medicalRecords = medicalRecords.stream()
                        .map(mr -> mr.getId() == medicalRecord.getId() ? medicalRecord : mr)
                        .collect(Collectors.toList());
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating medical record with ID " + medicalRecord.getId() + ": ", e);
            throw new RuntimeException("Error occurred while updating medical record", e);
        }
    }

    // Deletes a medical record by ID.
    public static boolean deleteMedicalRecord(int id) {
        try {
            return medicalRecords.removeIf(mr -> mr.getId() == id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting medical record with ID " + id + ": ", e);
            throw new RuntimeException("Error occurred while deleting medical record", e);
        }
    }
}
