package com.deshan.dao;

import com.deshan.model.Appointment;
import com.deshan.model.Doctor;
import com.deshan.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppointmentDAO {
    // Logger for logging error messages
    private static final Logger logger = Logger.getLogger(AppointmentDAO.class.getName());

    // List to store appointments
    private static List<Appointment> appointments = new ArrayList<>();

    // Method to retrieve all appointments
    public static List<Appointment> getAllAppointments() {
        try {
            return appointments;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all appointments: ", e);
            throw new RuntimeException("Error occurred while retrieving appointments", e);
        }
    }

    static {
        try {
            // Initializing some sample appointments
            Patient patient1 = new Patient("Tharaka", "1234567", 1234, 31, "male", "colombo", "Critical");
            Patient patient2 = new Patient("Sayuri", "1234557", 123, 23, "female", "colombo", "Good");
            Doctor doctor1 = new Doctor("deshan", "0786534234", 1, 31, "male", "colombo", "cardiac");
            Doctor doctor2 = new Doctor("John", "078589625", 2, 33, "male", "colombo", "neuro");
            appointments.add(new Appointment(3, "20.05.2024", "shan", patient1, doctor1));
            appointments.add(new Appointment(3, "06.07.2024", "shan", patient2, doctor2));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while initializing appointments: ", e);
            throw new RuntimeException("Error occurred while initializing appointments", e);
        }
    }

    // Method to retrieve an appointment by ID
    public static Appointment getAppointmentById(int id) {
        try {
            return appointments.stream()
                    .filter(a -> a.getId() == id)
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving appointment with ID " + id + ": ", e);
            throw new RuntimeException("Error occurred while retrieving appointment", e);
        }
    }

    // Method to add a new appointment
    public static boolean addAppointment(Appointment appointment) {
        try {
            return appointments.add(appointment);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while adding appointment: ", e);
            throw new RuntimeException("Error occurred while adding appointment", e);
        }
    }

    // Method to update an existing appointment
    public static boolean updateAppointment(Appointment appointment) {
        try {
            Appointment existingAppointment = getAppointmentById(appointment.getId());
            if (existingAppointment != null) {
                appointments = appointments.stream()
                        .map(a -> a.getId() == appointment.getId() ? appointment : a)
                        .collect(Collectors.toList());
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating appointment with ID " + appointment.getId() + ": ", e);
            throw new RuntimeException("Error occurred while updating appointment", e);
        }
    }

    // Method to delete an appointment by ID
    public static boolean deleteAppointment(int id) {
        try {
            return appointments.removeIf(a -> a.getId() == id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting appointment with ID " + id + ": ", e);
            throw new RuntimeException("Error occurred while deleting appointment", e);
        }
    }
}
