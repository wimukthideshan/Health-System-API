package com.deshan.dao;

import com.deshan.model.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Level;
import java.util.logging.Logger;

// Data Access Object (DAO) class for managing Doctor entities.
public class DoctorDAO {
    private static final Logger logger = Logger.getLogger(DoctorDAO.class.getName());

    // List to store all doctors
    private static List<Doctor> doctors = new ArrayList<>();

    // Retrieves all doctors.
    public static List<Doctor> getAllDoctors() {
        try {
            return doctors;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all doctors: ", e);
            throw new RuntimeException("Error occurred while retrieving doctors", e);
        }
    }

    // Initializing doctors
    static {
        try {
            doctors.add(new Doctor("deshan", "0786534234", 1, 31, "male", "colombo", "cardiac"));
            doctors.add(new Doctor("John", "078589625", 2, 33, "male", "colombo", "neuro"));
            doctors.add(new Doctor("Hiruni", "070248623", 3, 31, "female", "colombo", "ENT"));
            doctors.add(new Doctor("Ashely", "071584392", 4, 21, "female", "colombo", "Cancer"));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while initializing doctors: ", e);
            throw new RuntimeException("Error occurred while initializing doctors", e);
        }
    }

    // Retrieves a doctor by ID.
    public static Doctor getDoctorById(int id) {
        try {
            return doctors.stream()
                    .filter(d -> d.getId() == id)
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving doctor with ID " + id + ": ", e);
            throw new RuntimeException("Error occurred while retrieving doctor", e);
        }
    }

    // Adds a new doctor.
    public static boolean addDoctor(Doctor doctor) {
        try {
            return doctors.add(doctor);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while adding doctor: ", e);
            throw new RuntimeException("Error occurred while adding doctor", e);
        }
    }

    // Updates an existing doctor.
    public static boolean updateDoctor(Doctor doctor) {
        try {
            Doctor existingDoctor = getDoctorById(doctor.getId());
            if (existingDoctor != null) {
                doctors = doctors.stream()
                        .map(d -> d.getId() == doctor.getId() ? doctor : d)
                        .collect(Collectors.toList());
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating doctor with ID " + doctor.getId() + ": ", e);
            throw new RuntimeException("Error occurred while updating doctor", e);
        }
    }

    // Deletes a doctor by ID.
    public static boolean deleteDoctor(int id) {
        try {
            return doctors.removeIf(d -> d.getId() == id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting doctor with ID " + id + ": ", e);
            throw new RuntimeException("Error occurred while deleting doctor", e);
        }
    }
}
